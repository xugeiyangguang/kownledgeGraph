package util;//import edu.uestc.auto.reasoning.utils.util.GraphBaseUtils;
import org.neo4j.driver.v1.types.Node;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

/**
 * @Program:mathematics
 * @description:创建概念知识图谱
 * @Author:dlh
 * @Date:2019-07-15-11-01
 */
public class createConceptKG {
    public static List<String> classes=new ArrayList<>();
    public static String path="C:\\Users\\27124\\IdeaProjects\\graphknowledge_xy\\src\\main\\java";
    //public static String path="mathematics-model\\src\\main\\java";
    //public static String path="mathematics\\mathematics-model\\src\\main\\java";
    /**
     * 创建节点，将mathematics-model下面的entity包里面的类创建为实体
     * @param names
     * @throws Exception
     */
    public static void ceateNodes(List<String> names) throws Exception{
        for (String name:names){
            Class cls=Class.forName(name);
            String type =name.split("\\.")[name.split("\\.").length-2];
            Method[] methods=cls.getDeclaredMethods();
            Map<String,String> properties=new HashMap<>();
            String ChineseName="";
            //得到Chinese类，将其添加为属性。
            for(Method method:methods){
                if(method.getName().equals("getChineseName")&& method.getReturnType()==String.class){
                    ChineseName=(String)method.invoke(null);
                }
                if(method.getName().equals("getProperties") && method.getReturnType()==Map.class){
                    properties=(Map<String, String>)method.invoke(null);
                }
            }
            if(!classes.contains(cls.getSimpleName())){
                GraphBaseUtils.createNodeWithLabel(cls.getSimpleName(),"entity",type);
                GraphBaseUtils.addNodeProperties(cls.getSimpleName(),"Concept:"+type,"ChineseName",ChineseName);
                GraphBaseUtils.addNodeProperties(properties,cls.getSimpleName(),"Concept:"+type);
            }

        }
    }

    /**
     * 将mathematics-model下面的relation包里面的类创建为关系
     * @param names
     * @throws Exception
     */
    public static int addRelations(List<String> names) throws Exception{
        int relationNumber = 0;
        for(String name:names){
            Class cls=Class.forName(name);
            String type =name.split("\\.")[name.split("\\.").length-2];
            Method[] methods=cls.getDeclaredMethods();
            List<String> startNodes=new ArrayList<>();
            List<String> endNodes=new ArrayList<>();
            Map<String,String> properties=new HashMap<>();
            String ChineseName="";

            for(Method method:methods){
                if(method.getName().equals("getStartNodes")&&method.getReturnType()==List.class){
                    startNodes=(List<String>)method.invoke(null);
                    relationNumber++;
                }
                if(method.getName().equals("getEndNodes")&&method.getReturnType()==List.class){
                    endNodes=(List<String>)method.invoke(null);
                }
                if(method.getName().equals("getChineseName")&&method.getReturnType()==String.class){
                    ChineseName=(String)method.invoke(null);
                }
                if(method.getName().equals("getProperties")&&method.getReturnType()==Map.class){
                    properties=(Map<String,String>)method.invoke(null);
                }
            }
            if(startNodes.size()!=endNodes.size()){
                continue;
            }

            for (int i = 0; i < startNodes.size(); i++) {
                System.out.println(startNodes.get(i) + " " + endNodes.get(i) + " " + cls.getSimpleName() + " " + type);
                GraphBaseUtils.createRelationship(startNodes.get(i), endNodes.get(i), true, cls.getSimpleName(), type);
                GraphBaseUtils.addPropertiesForRelatoin(cls.getSimpleName(),type,"ChineseName",ChineseName);
                GraphBaseUtils.addRelationShipProperties(properties, cls.getSimpleName(), type);
            }

        }
        return relationNumber;
    }
    /**
     * 添加关系“有子类”
     * @param names  entity包下的类名  （实体名）
     * @throws ClassNotFoundException
     */
    public static int addHasSubclassRealtion (List<String> names) throws ClassNotFoundException{
        int subClassNumber = 0;
        for (String name : names) {
            Class cls = Class.forName(name);
            System.out.println(cls.getSimpleName());
            Class[] classes = cls.getInterfaces();
            for (Class c : classes) {
                System.out.println("Interfaces :   " + c.getSimpleName());
                GraphBaseUtils.createRelationship(c.getSimpleName(), cls.getSimpleName(), true, "有子类", "hasSubclass");
            }

            if (!cls.isInterface()) {
                System.out.println("Superclass :   " + cls.getSuperclass().getSimpleName());
                if (names.contains(cls.getName())) {
                    subClassNumber++;
                    GraphBaseUtils.createRelationship(cls.getSuperclass().getSimpleName(), cls.getSimpleName(), true, "有子类", "hasSubclass");
                }
            }
        }
        return subClassNumber;
    }
    /**
     * 添加关系的属性
     * @param properties  属性名和属性值
     * @param relationshipName   关系名
     * @param relationLabel      关系标签
     */
    public static void addRelationShipProperties(Map<String, String> properties, String relationshipName,
                                                 String relationLabel){
        for (String property : properties.keySet()) {
            GraphBaseUtils.addNodeProperties(relationshipName, relationLabel, property, properties.get(property));
        }
    }

    public static void addNodeProperties(Map<String, String> properties, String relationshipName,
                                         String relationLabel) {
        for (String property : properties.keySet()) {
            GraphBaseUtils.addNodeProperties(relationshipName, relationLabel, property, properties.get(property));
        }
    }
    /**
     * 创建节点 添加“有子类” 关系
     * @param names
     * @throws Exception
     */
    public static int getAllClassesAndInterfacesByName (List<String> names) throws Exception{
        ceateNodes(names);
        int re = addHasSubclassRealtion(names);
        return re;
    }

    /**
     * 得到所有的类和接口的名字
     * @param path
     * @return
     */
    public static List<String> getAllClassesAndInterfacesName (String path) {
        File file = new File(path);
        List<String> nameResults = new ArrayList<>();
        if (file.exists()) {
            List<File> list = new LinkedList<>();

            List<File> fileResults = new ArrayList<>();
            File[] files = file.listFiles();

            for (File tempFile : files) {
                if (tempFile.isDirectory()) {
                    list.add(tempFile);
                }
                else {
                    fileResults.add(tempFile);
                }
            }

            File tempFiles;
            while (!list.isEmpty()) {
                tempFiles = list.remove(0);
                files = tempFiles.listFiles();
                for (File tempFile : files) {
                    if (tempFile.isDirectory()) {
                        list.add(tempFile);
                    }
                    else {
                        fileResults.add(tempFile);
                    }
                }
            }
            fileResults.forEach(result -> {
                nameResults.add(result.getPath().replace(path + "\\", "").replace("\\", ".").replace(".java", ""));
            });
        }
        return nameResults;
    }
    /**
     * 获得一个节点的某个属性的属性值  节点为带有标签“Concept”的节点
     */
    public static void getAllPropertiesOfANode () {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("请输入节点的名字：");
            String name = sc.nextLine();
            if (name.equals("exit")) {
                return;
            }
            if (GraphBaseUtils.getNodeWithLabelAndName("Concept", name, "name") == null &&
                    GraphBaseUtils.getNodeWithLabelAndName("Concept", name, "ChineseName") == null) {
                System.out.println("该节点不存在！");
                continue;
            }
            System.out.println(name + ": ");
            List<String> p = GraphBaseUtils.getAllPropertiesOfNode(name);
            if (p.size() == 0) {
                System.out.println("节点" + name + "没有属性！");
                continue;
            }
            for (String ps : p) {
                String value = GraphBaseUtils.getMarkedPropertyOfNode("Concept", name, ps);
                if (value == null) {
                    System.out.println("节点" + name + "没有属性" + ps + "！");
                    continue;
                }
                System.out.print(ps + ": " + value + "  ");
            }
            System.out.println();
        }

    }


    /**
     * 获得所有的某个属性的属性值  节点为带有标签“Concept”的节点
     */
    public static void getAllPropertiesOfAllNode (List<String> dataNames) throws Exception{
        for (String name : dataNames) {
            Class cls = Class.forName(name);
            System.out.println(cls.getSimpleName() + ": ");
            List<String> p = GraphBaseUtils.getAllPropertiesOfNode(cls.getSimpleName());
            for (String ps : p) {
                String value = GraphBaseUtils.getMarkedPropertyOfNode("Concept", cls.getSimpleName(), ps);
                System.out.print(ps + ": " + value + "  ");
            }
            System.out.println();
        }

    }
    /**
     * 处理输入的节点
     * @return
     *         "false"：节点不存在
     *         "exit"：退出指令
     */
    public static String inputNode () {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (name.equals("exit")) {
            return "exit";
        }
        if (GraphBaseUtils.getNodeWithLabelAndName("Concept", name, "name") == null &&
                GraphBaseUtils.getNodeWithLabelAndName("Concept", name, "ChineseName") == null) {
            return "false";
        }
        return name;
    }

    /**
     * 通过节点的中文名或者英文名来获得节点
     * @param label   节点的标签
     * @param name     节点的名字
     * @return   不存在为null   存在则返回该节点
     */
    public static Node getNodeByChineseNameOrName (String label, String name) {
        if (GraphBaseUtils.getNodeWithLabelAndName(label, name, "ChineseName") != null) {
            return GraphBaseUtils.getNodeWithLabelAndName(label, name, "ChineseName");
        }
        if (GraphBaseUtils.getNodeWithLabelAndName(label, name, "name") != null) {
            return GraphBaseUtils.getNodeWithLabelAndName(label, name, "name");
        }
        return null;
    }

    // 给一个接口，返回这个接口的所有实现类
    public static List getAllClassByInterface(Class c) {
        List returnClassList = new ArrayList(); // 返回结果
        // 如果不是一个接口，则不做处理
        if (c.isInterface()) {
            String packageName = c.getPackage().getName(); // 获得当前的包名
            try {
                List<Class<?>> allClass = getClasses(packageName); // 获得当前包下以及子包下的所有类
                // 判断是否是同一个接口
                for (int i = 0; i < allClass.size(); i++) {
                    Class<?> clazz = allClass.get(i);
                    if (!c.isAssignableFrom(clazz) || c.equals(clazz) || Modifier.isAbstract(clazz.getModifiers()))
                        continue;
                    returnClassList.add(allClass.get(i).getName());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnClassList;
    }

    // 从一个包中查找出所有的类，在jar包中不能查找
    private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }
    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static void main(String[] args) throws Exception {

//        classes.add("edu.uestc.auto.reasoning.knowledgegraph.util.createConceptKG");
        classes.add("util.createConceptKG");
//        classes.add("edu.uestc.auto.reasoning.utils.util.GraphBaseUtils");
        classes.add("util.DriverSingleton");
        classes.add("util.GraphBaseUtils");
        List<String> names=createConceptKG.getAllClassesAndInterfacesName(path);
        List<String> dataNames=new ArrayList<>();
        List<String> relationNames=new ArrayList<>();
        for (String name : names) {
            if (name.contains("data.")) {
                dataNames.add(name);
            }

            if (name.contains("relation.") && !name.contains(".conclusion.")&& !name.contains(".notsure.") ) {
                relationNames.add(name);
            }
        }
        GraphBaseUtils.deleteGraphDBWithLabel("Concept");
        int subNum = createConceptKG.getAllClassesAndInterfacesByName(dataNames);
        int otherNum = createConceptKG.addRelations(relationNames);
        System.out.println("一共包含关系：" + subNum + otherNum + "个");
    }
}




