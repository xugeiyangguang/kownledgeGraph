package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.io.File;

import java.util.*;

/**
 * @author cyq7on, dlh
 * @description 知识图谱基础工具类
 * @create 2019/7/10
 **/
public class GraphBaseUtils {
    private static final Logger LOG = LogManager.getLogger(GraphBaseUtils.class);

    /**
     * 删除所有带有label标签的节点
     *
     * @param label
     */
    public static void deleteGraphDBWithLabel(String label) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n:" + label + ") detach delete n";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 删除所有节点关系
     */
    public static void deleteGraphDB() {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n) detach delete n";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * \
     * 为关系添加属性
     *
     * @param relationshipName  关系名字（name属性）
     * @param relationshipLabel 关系的标签
     * @param property          属性的名字
     * @param propertyValue     属性的值
     */
    public static void addRelationshipProperties(String relationshipName, String relationshipLabel,
                                                 String property, String propertyValue) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match () -[r:" + relationshipLabel + "{name : '" + relationshipName + "'}]->()"
                    + "set r." + property + " = " + "'" + propertyValue + "'";
            tx.run(cypher);
            tx.success();
            tx.close();
        }
    }

    /**
     * \
     * 添加关系的属性
     *
     * @param properties       属性名和属性值
     * @param relationshipName 关系名
     * @param relationLabel    关系标签
     */
    public static void addRelationShipProperties(Map<String, String> properties, String relationshipName,
                                                 String relationLabel) {
        for (String property : properties.keySet()) {
            GraphBaseUtils.addRelationshipProperties(relationshipName, relationLabel, property, properties.get(property));
        }
    }

    /**
     * 在概念知识图谱中创建节点，赋予两个标签label1，label2.并且给与节点名字。
     *
     * @param nodeName
     * @param Label1
     * @param Label2
     */
    public static void createNodeWithLabel(String nodeName, String Label1, String Label2) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "CREATE (" + nodeName + " : Concept :" + Label1 + ":" + Label2 + "{ name : '"
                    + nodeName + "'} )";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }

    }

    /**
     * 创建带有定理标签的节点
     *
     * @param label
     * @param nodeName
     */
    public static void createNodeWithTheoremLabel(String label, String nodeName) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "create(:Theorem:" + label + "{name:'" + nodeName + "'})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 在概念知识图谱中通过节点名字查找该节点
     *
     * @param nodeName
     * @return 返回概念知识图谱中的节点名字
     */
    public static String getNodeName(String nodeName) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match(n:Concept) return n.name";
            StatementResult result = tx.run(cypher);
            tx.success();
            return result.next().get(0).asString();
        }
    }

    /**
     * 在定理知识图谱中创建两个节点之间的关系
     *
     * @param startNode         节点名
     * @param endNode           节点名
     * @param flag              如果flag=true，从startNode指向endNode，如果flag=false，从endNode指向startNode
     * @param relationshipName  关系名
     * @param relationshipLabel 关系标签
     */
    public static void createTheoremRelationship(String startNode, String endNode, boolean flag, String relationshipName, String relationshipLabel) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String s = "create(m) -[:" + relationshipLabel + "{name:'" + relationshipName + "'}]->(n)";
            if (flag) {
                s = "create(n) -[:" + relationshipLabel + "{name:'" + relationshipName + "'}]->(m)";
            }
            String cypher = "match (n:Theorem{name:'" + startNode + "'})" + "match(m:Theorem{name:'" + endNode + "'})" + s;
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 在概念知识图谱中创建两个节点之间的关系
     *
     * @param startNode         节点名
     * @param endNode           节点名
     * @param flag              如果flag=true，从startNode指向endNode，如果flag=false，从endNode指向startNode
     * @param relationshipName  关系名
     * @param relationshipLabel 关系标签
     */
    public static void createRelationship(String startNode, String endNode, boolean flag, String relationshipName, String relationshipLabel) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String s = "create(m) -[:" + relationshipLabel + "{name:'" + relationshipName + "'}]->(n)";
            if (flag) {
                s = "create(n) -[:" + relationshipLabel + "{name:'" + relationshipName + "'}]->(m)";
            }
            String cypher = "match (n: Concept{name:'" + startNode + "'})" + "match(m:Concept{name:'" + endNode + "'})" + s;
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 替换节点的名字
     *
     * @param oldName
     * @param newName
     */
    public static void replaceNodeName(String oldName, String newName) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match(n:Concept{name:'" + oldName + "'}) set n.name='" + newName + "'";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 得到节点的所有标签
     *
     * @param node
     * @return 节点的所有标签
     */
    public static String dealNodeLabels(Node node) {
        if (node == null) {
            return null;
        }
        String label = "";
        Iterator<String> startLabels = node.labels().iterator();
        while (startLabels.hasNext()) {
            label += startLabels.next().trim() + ":";
        }
        label = label.substring(0, label.length() - 1);
        return label;
    }

    /**
     * 以节点node为开始节点或者结束节点，找到所有与node相连的节点（不定个数，特定的关系），关系的方向不能改变，关系的名字和标签都一样
     * eg: node-(r)->node1-(r)->node2......-(r)->noden
     *
     * @param node              开始节点或者结束节点
     * @param relationshipLabel 关系的标签
     * @param flag              flag=true，node为开始节点（->） ,flag=false,node为结束节点（<-）
     * @return 返回的是该路径上的所有节点
     */
    public static List<Node> getAllNodesHasRelation(Node node, String relationshipLabel, boolean flag) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            List<Node> nodes = new ArrayList<>();
//            if(node.get("name").asString()==null){
//                LOG.debug("查询的实体不存在！");
//                return nodes;
//            }else {
            String name = node.get("name").asString();
            String label = dealNodeLabels(node);
            String s1 = "<-";
            String s2 = "-";
            if (flag) {
                s1 = "-";
                s2 = "->";
            }
            String cypher = "match (:" + label + "{name:'" + name + "'})" + s1 +
                    "[:" + relationshipLabel + "*1..10]" + s2 + "(n) return n";
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return new ArrayList<>();
            }
            while (result.hasNext()) {
                nodes.add(result.next().get(0).asNode());
            }
//            }
            tx.success();
            tx.close();
            return nodes;
        }
    }

    /**
     * 获取指定的名称节点的指定关系节点
     * 许阳
     */
    public static void getAllNodesHasRelation1(String startName, String relationshipName, boolean flag) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String s1 = "<-";
            String s2 = "-";
            if (flag) {
                s1 = "-";
                s2 = "->";
            }
            //match (n:代数{name:"函数"})-[:relation{name:"前置关系"}]->(m) return m
            String cypher = "match (m{name:'" + startName + "'})" + s1 +
                    "[r{name:'" + relationshipName + "'}]" + s2 + "(n) return n";
            LOG.debug(cypher);
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return;
            }
            while (result.hasNext()) {
                String tmp = result.next().get(0).get("name").toString();
                System.out.println(startName + "-" + relationshipName + "->" + tmp.substring(1, tmp.length() - 1));
            }
//            }
            tx.success();
            tx.close();

            //   System.out.println(re);
        }
    }


    /**
     * @param startName 开始节点的名称
     * @param flag      为1表示是开始节点
     *                  输出某一结点的所有相邻节点
     *                  许阳
     */

    public static HashMap<String,String> getAllNodesHasRelation2(String startName) {
        HashMap<String,String> re = new HashMap<>();   //存储相关的知识点
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            //match (n:代数{name:"函数"})-[r]->(m:代数) return n,m,r
            String cypher = String.format("match (n{name:\"%s\"})<-[r]-(m) return n,m,r", startName);
            //   LOG.debug(cypher);
            StatementResult result = tx.run(cypher);
            //   List<String> re = new ArrayList<>();
            /*if (!result.hasNext()) {
                return null;
            }*/
            String s = "";while (result.hasNext()) {
                Record tmp = result.next();
                String start = tmp.get(0).get("name").toString();
                String end = tmp.get(1).get("name").toString();
                String relation = tmp.get(2).get("name").toString();
                s = end + "-" + relation + "->" + start;
                String endTmp1 = end.replace("\"", "");
                String rel = relation.replace("\"", "");
                if (rel.equals("有前驱")){
                    re.put(endTmp1,"前驱关系");
                }else if(rel.equals("有子类")){
                    re.put(endTmp1,"子类关系");
                }else if(rel.equals("有属性")){
                    re.put(endTmp1,"属性关系");
                }else if(rel.equals("有考点")){
                    re.put(endTmp1,"考点关系");
                }else if(rel.equals("有部分")){
                    re.put(endTmp1,"组成关系");
                }


                System.out.println(s);
            }

            String cypher1 = String.format("match (n{name:\"%s\"})-[r]->(m) return n,m,r", startName);
            //   LOG.debug(cypher);
            StatementResult result1 = tx.run(cypher1);
            //   List<String> re = new ArrayList<>();
            /*if (!result1.hasNext()) {
                return null;
            }*/
            while (result1.hasNext()) {
                Record tmp = result1.next();
                String start = tmp.get(0).get("name").toString();
                String end = tmp.get(1).get("name").toString();
                String relation = tmp.get(2).get("name").toString();
                s = start + "-" + relation + "->" + end;
                String endTmp1 = end.replace("\"", "");
                String rel = relation.replace("\"", "");
                if (rel.equals("有前驱")){
                    re.put(endTmp1,"前驱关系");
                }else if(rel.equals("有子类")){
                    re.put(endTmp1,"子类关系");
                }else if(rel.equals("有属性")){
                    re.put(endTmp1,"属性关系");
                }else if(rel.equals("有考点")){
                    re.put(endTmp1,"考点关系");
                }else if(rel.equals("有部分")){
                    re.put(endTmp1,"组成关系");
                }

                //   re.add(endTmp1);

                System.out.println(s);

            }

//            }
            tx.success();
            tx.close();

            //   System.out.println(re);
        }
        return re;
    }


    /**
     * 通过节点的标签1（在概念知识图谱中通常设置为Concept），
     * 标签2，标签2的具体名字（在概念知识图谱中一般是ChineseName的值，如“直线”）
     *
     * @param label1
     * @param label2Value
     * @param label2
     * @return
     */
    public static Node getNodeWithLabelAndName(String label1, String label2Value, String label2) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match(n:" + label1 + "{" + label2 + ":'" + label2Value + "'}) return n";
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return null;
            }
            Node node = result.next().get(0).asNode();
            tx.success();
            tx.close();
            return node;
        }

    }

    /**
     * 查找与节点相连的节点
     *
     * @param name
     * @param relationLabel
     * @return
     */
    public static String getFullNodeName(String name, String relationLabel) {
///        Map<String,List<String>> map=new HashMap<>();
        String res = "";
        List<Node> nodes = new ArrayList<>();
        if (GraphBaseUtils.getNodeName(name) == null) {
            LOG.debug("该节点名在数据库中不存在！");
            return res;
        } else {
            Node node = getNodeWithLabelAndName("Concept", name, "ChineseName");
            nodes = GraphBaseUtils.getAllNodesHasRelation(node, relationLabel, false);
            int n = nodes.size();
            int i = 0;
            while (n > 0) {
                res = res.concat(nodes.get(i).get("name").toString());
                n--;
                i++;
            }
        }
        res = res.replace('"', '.');
        return res;
    }

    /**
     * 为一个节点添加属性
     *
     * @param nodeName
     * @param nodeLabel
     * @param property
     * @param propertyValue
     */
    public static void addPropertiesForNode(String nodeName, String nodeLabel, String property, String propertyValue) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n: " + nodeLabel + " {name : '" + nodeName + "' })"
                    + "set n." + property + " = " + "'" + propertyValue + "'";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }

    }

    /**
     * 为关系添加属性
     *
     * @param relationshipName
     * @param relationshipLabel
     * @param property
     * @param propertyValue
     */
    public static void addPropertiesForRelatoin(String relationshipName, String relationshipLabel, String property, String propertyValue) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match () -[r:" + relationshipLabel + "{name : '" + relationshipName + "'}]->()"
                    + "set r." + property + " = " + "'" + propertyValue + "'";
            tx.run(cypher);
            tx.success();
            tx.close();
        }
    }

    /**
     * 得到指定名字的所有属性，中英文名字的节点都可以。
     *
     * @param name
     * @return
     */
    public static List<String> getAllPropertiesOfNode(String name) {
        List<String> properties = new ArrayList<>();
        String ChineseName = "ChineseName : '";
        String EnglishName = "name :'";
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n : Concept {" + ChineseName + name + "'}) return keys(n)";
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                cypher = "match (n : Concept {" + EnglishName + name + "'}) return keys(n)";
                result = tx.run(cypher);
            }
            if (!result.hasNext()) {
                return properties;
            }
//            while (result.hasNext()) {
            List<Object> objects = result.next().get(0).asList();
            for (Object o : objects) {
                properties.add((String) o);
//                }
//                properties.addAll(result.next().get(0).asList());
            }

        }
        return properties;
    }

    /**
     * 获取某个节点特定的属性的值
     *
     * @param label
     * @param name
     * @param property
     * @return
     */
    public static String getMarkedPropertyOfNode(String label, String name, String property) {
        String ChineseName = "ChineseName : '";
        String EnglishName = "name : '";
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n :" + label + "{" + ChineseName + name + "'}) return n." + property;
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                cypher = "match (n :" + label + "{" + EnglishName + name + "'}) return n." + property;
                result = tx.run(cypher);
            }
            if (!result.hasNext()) {
                return null;
            }
            tx.success();
            tx.close();
            return result.next().get(0).asString();
        }
    }

    /**
     * @param startNode
     * @param endNode
     * @param relationshipLabel
     * @param relationshipName
     * @return
     */
    public static Relationship getRelationshipOfNodes(Node startNode, Node endNode, String relationshipLabel, String relationshipName) {
        if (startNode == null || endNode == null) {
            return null;
        }
        String startName = startNode.get("name").asString();
        String endName = endNode.get("name").asString();
        String startLabel = dealNodeLabels(startNode);
        String endLabel = dealNodeLabels(endNode);
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher =
                    "match (n : " + startLabel + "{name : '" + startName + "'}) "
                            + "match (m : " + endLabel + "{name : '" + endName + "'}) "
                            + "match (n) -[r:" + relationshipLabel + "{name : '" + relationshipName + "'}]-> (m)"
                            + " return r";
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return null;
            }
            Relationship relationship = result.next().get(0).asRelationship();
            tx.success();
            tx.close();
            return relationship;
        }
    }

    /**
     * 创建两个节点之间的关系
     *
     * @param startNode
     * @param endNode
     * @param relationShipLabel 关系的标签
     * @param relationShipName  关系的名字
     */
    public static void createRelationShip(Node startNode, Node endNode, String relationShipLabel, String relationShipName) {
        String startName = startNode.get("name").asString();
        String endName = endNode.get("name").asString();
        String startLabel = "";
        Iterator<String> startLabels = startNode.labels().iterator();
        while (startLabels.hasNext()) {
            startLabel += startLabels.next().trim() + ":";
        }
        String endLabel = "";
        Iterator<String> endLabels = endNode.labels().iterator();
        while (endLabels.hasNext()) {
            endLabel += endLabels.next().trim() + ":";
        }
        startLabel = startLabel.substring(0, startLabel.length() - 1);
        endLabel = endLabel.substring(0, endLabel.length() - 1);
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher =
                    "match (n : " + startLabel + "{name : '" + startName + "'}) "
                            + "match (m : " + endLabel + "{name : '" + endName + "'}) "
                            + "create (n) -[:" + relationShipLabel + "{name : '" + relationShipName + "'}]-> (m)";
            tx.run(cypher);
            tx.success();
            tx.close();
        }
    }

    /**
     * \
     * 通过关系的开始节点，结束节点，标签和名字查找关系
     *
     * @param startNode 开始节点
     * @param endNode   结束节点
     * @return 如果关系存在返回找到的关系，如果没找到则返回空的  List<Relationship>
     */
    public static List<Relationship> getSpecileAllRelationship(Node startNode, Node endNode) {
        List<Relationship> relationships = new ArrayList<>();
        if (startNode == null || endNode == null) {
            return relationships;
        }
        String startName = startNode.get("name").asString();
        String endName = endNode.get("name").asString();
        String startLabel = dealNodeLabels(startNode);
        String endLabel = dealNodeLabels(endNode);
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher =
                    "match (n : " + startLabel + "{name : '" + startName + "'}) "
                            + "match (m : " + endLabel + "{name : '" + endName + "'}) "
                            + "match (n) -[r]-> (m)"
                            + " return r";
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return relationships;
            }
            while (result.hasNext()) {
                relationships.add(result.next().get(0).asRelationship());
            }
            tx.success();
            tx.close();
            return relationships;
        }
    }

    /**
     * \
     * 获得某一个节点（结束节点）特定关系连接的所有节点
     *
     * @param nodeName         结束节点的名字
     * @param relationshipName 特定关系的名字
     * @return
     */
    /**
     * 得到所有的类和接口的名字
     *
     * @param path
     * @return
     */
    public static List<String> getAllClassesAndInterfacesName(String path) {
        File file = new File(path);
        List<String> nameResults = new ArrayList<>();
        if (file.exists()) {
            List<File> list = new LinkedList<>();

            List<File> fileResults = new ArrayList<>();
            File[] files = file.listFiles();

            for (File tempFile : files) {
                if (tempFile.isDirectory()) {
                    list.add(tempFile);
                } else {
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
                    } else {
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

    public static List<Node> getAllEndNodeWtheRelationshipName(String relationshipLabel, String relationshipName, String nodeName) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            List<Node> nodes = new ArrayList<>();
            String cypher = "match ({name : '" + nodeName + "'}) <-[:" + relationshipLabel + "{name : '"
                    + relationshipName + "'}]- (n) return n";
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return new ArrayList<>();
            }
            while (result.hasNext()) {
                nodes.add(result.next().get(0).asNode());
            }
            tx.success();
            tx.close();
            return nodes;
        }
    }

    public static void addNodeProperties(Map<String, String> properties, String relationshipName,
                                         String relationLabel) {
        for (String property : properties.keySet()) {
            GraphBaseUtils.addNodeProperties(relationshipName, relationLabel, property, properties.get(property));
        }
    }

    /**
     * 为节点添加属性
     *
     * @param nodeName
     * @param nodeLabel
     * @param property
     * @param propertyValue
     */
    public static void addNodeProperties(String nodeName, String nodeLabel, String property, String propertyValue) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n: " + nodeLabel + " {name : '" + nodeName + "' })"
                    + "set n." + property + " = " + "'" + propertyValue + "'";
            tx.run(cypher);
            tx.success();
            tx.close();
        }
    }

    /**
     * \
     * 获得某个特定关系的value
     *
     * @param startnode         关系的开始节点
     * @param endNode           关系的结束节点
     * @param relationshipLabel 关系的标签
     * @param relationshipName  关系的名字
     * @param propertyName      要修改的属性名字
     * @param value             要修改的值
     */
    public static String getRelationshipPropertyWithTowNode(Node startnode, Node endNode, String relationshipLabel, String relationshipName, String propertyName,
                                                            String value) {
        String startNodeName = startnode.get("name").asString();
        String endNodeName = endNode.get("name").asString();
        String nodeLabel = startnode.labels().iterator().next().trim();
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n : " + nodeLabel + "{name : '" + startNodeName + "'}) -[r:" + relationshipLabel
                    + "{name : '" + relationshipName + "'}]->(: " + nodeLabel + "{name : '" + endNodeName + "'}) return r." + propertyName;
            StatementResult result = tx.run(cypher);
            if (!result.hasNext()) {
                return null;
            }
            tx.success();
            tx.close();
            return result.next().get(0).asString();
        }
    }

    /**
     * \
     * 设置某个特定关系的value
     *
     * @param startnode         关系的开始节点
     * @param endNode           关系的结束节点
     * @param relationshipLabel 关系的标签
     * @param relationshipName  关系的名字
     * @param propertyName      要修改的属性名字
     * @param value             要修改的值
     */
    public static void setRelationshipPropertyWithTowNode(Node startnode, Node endNode, String relationshipLabel, String relationshipName, String propertyName,
                                                          String value) {
        String startNodeName = startnode.get("name").asString();
        String endNodeName = endNode.get("name").asString();
        String nodeLabel = startnode.labels().iterator().next().trim();
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match (n : " + nodeLabel + "{name : '" + startNodeName + "'}) -[r:" + relationshipLabel
                    + "{name : '" + relationshipName + "'}]->(: " + nodeLabel + "{name : '" + endNodeName + "'}) set r." + propertyName + "= '" + value + "'";
            tx.run(cypher);
            tx.success();
            tx.close();
        }
    }

    /**
     * 两节点间是否存在关系
     *
     * @param node1
     * @param node2
     * @return
     */
    public static String getRelationByTwoNodes(String node1, String node2) {
        String cql = String.format("match(n{name:\"%s\"})-[r]->(m{name:\"%s\"}) return r.name", node1, node2);
        LOG.debug(cql);
        String name = null;
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            StatementResult result = tx.run(cql);
            List<Record> records = result.list();
            if (!records.isEmpty()) {
                name = records.get(0).get(0).asString();
            }
            tx.success();
        }
        return name;
    }

    /**
     * 根据属性查找节点
     *
     * @param key
     * @param val
     * @return
     */
    public static List<String> getNodes(String key, String val) {
        String cql = String.format("match(n) where n.%s = \"%s\" return n.name", key, val);
        LOG.debug(cql);
        List<String> nodes = new ArrayList<>(2);
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            StatementResult result = tx.run(cql);
            List<Record> records = result.list();
            if (!records.isEmpty()) {
                for (Record record : records) {
                    nodes.add(record.get(0).asString());
                }
            }
            tx.success();
        }
        return nodes;
    }

    public static List<String> getNodesByLinkedNode(String nodeName) {
        List<String> nodes = new ArrayList<>();
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = String.format("MATCH (n{ChineseName:\"%s\"}) -[]-> (m) RETURN m.ChineseName", nodeName);
            String cypher2 = String.format("MATCH (n) -[]-> (m{ChineseName:\"%s\"}) RETURN n.ChineseName", nodeName);
            LOG.debug(cypher);
            LOG.debug(cypher2);
            StatementResult result = tx.run(cypher);
            StatementResult result2 = tx.run(cypher2);
            List<Record> records = result.list();
            List<Record> records2 = result2.list();
            if (!records.isEmpty()) {
                for (Record record : records) {
                    nodes.add(record.get(0).asString());
                }
            }
            if (!records2.isEmpty()) {
                for (Record record : records2) {
                    nodes.add(record.get(0).asString());
                }
            }
        }
        return nodes;
    }

    /**
     * 创建问题节点
     *
     * @param asking
     * @param typeName
     * @param nodeName
     * @param questionID
     */
    public static void createQuestionNode(String asking, String typeName, String nodeName, String questionID) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            if (typeName.equals(asking)) {
                String cypher = "create(:" + questionID + "{typeName:'" + typeName + "',name:'" + nodeName + "',asking:'" + asking + "'})";

                LOG.debug(cypher);
                tx.run(cypher);
                tx.success();
            } else {
                String cypher = "create(:" + questionID + "{typeName:'" + typeName + "',name:'" + nodeName + "'})";

                LOG.debug(cypher);
                tx.run(cypher);
                tx.success();
            }

        }
    }


    public static void createQuestionRelationship(String startNode, String endNode, boolean flag, String relationshipName, String isConclusion, String questionID) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String s = "create(m) -[:" + questionID + "{name:'" + relationshipName + "'}]->(n)";
            if (flag) {
                s = "create(n) -[:" + questionID + "{name:'" + relationshipName + "',isConclusion:'" + isConclusion + "'}]->(m)";
            }
            String cypher = "match (n: " + questionID + "{name:'" + startNode + "'})" + "match(m:" + questionID + "{name:'" + endNode + "'})" + s;
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 输入两个节点中文名返回节点之间的关系
     *
     * @param node1
     * @param node2
     * @return
     */
    public static List<String> getRelationsBetweenTwoNodes(String node1, String node2) {
        List<String> relations = new ArrayList<>();
        String cypher = String.format("match(n{name:\"%s\"})-[r]->(m{name:\"%s\"}) return r.name", node1, node2);
        LOG.debug(cypher);
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            StatementResult result = tx.run(cypher);
            List<Record> records = result.list();
                for (Record record : records) {
                    if (!record.get(0).asString().equals("null")) {
                        relations.add(record.get(0).asString());
                    }

            }
            tx.success();
            tx.close();
        }
        return relations;
    }

    /**
     * 返回关系的属性
     *
     * @param relationName
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getPropertiesOfRelation(String relationName) throws Exception {
        Map<String, Object> res = new HashMap<>();
        String cypher = String.format("match(n)-[r{ChineseName:\"%s\"}]->(m) return r", relationName);
        LOG.debug(cypher);
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            StatementResult result = tx.run(cypher);
            List<Record> records = result.list();
            Value value = records.get(0).values().get(0);
            RelationshipValue relationshipValue = (RelationshipValue) value;
            InternalRelationship relationship = (InternalRelationship) relationshipValue.asObject();
            Map<String, Object> map = relationship.asMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (!entry.getKey().equals("ChineseName")) {
                    res.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return res;
    }

    /**
     * 返回两个节点之间的关系及其关系的属性
     *
     * @param node1
     * @param node2
     * @return
     * @throws Exception
     */
    public static Map<String, Map<String, Object>> getPropertiesBetweenTwoNodes(String node1, String node2) throws Exception {
        Map<String, Map<String, Object>> map = new HashMap<>();
        List<String> relations = getRelationsBetweenTwoNodes(node1, node2);
        for (String relation : relations) {
            map.put(relation, getPropertiesOfRelation(relation));
        }
        if (map.size() == 0) {
            relations = getRelationsBetweenTwoNodes(node2, node1);
            for (String relation : relations) {
                map.put(relation, getPropertiesOfRelation(relation));
            }
        }
        return map;
    }

    /**
     * 得到某个节点的所有属性
     *
     * @param name
     */
    public static void getProperty(String name) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            //match (n:代数{name:"函数"})-[r]->(m:代数) return n,m,r
            String cypher = String.format("match (n{name:\"%s\"}) return n", name);
            //   LOG.debug(cypher);
            StatementResult result = tx.run(cypher);
            //   List<String> re = new ArrayList<>();
            if (!result.hasNext()) {
                return;
            }
            while (result.hasNext()) {
                Record tmp = result.next();
                String start = tmp.get(0).get("name").toString();

                System.out.println("name:" + start);
            }
//            }
            tx.success();
            tx.close();

            //   System.out.println(re);
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(getPropertiesBetweenTwoNodes("数列","数列的项"));
        //   System.out.println(getPropertiesOfRelation("值关系"));
           System.out.println(getRelationsBetweenTwoNodes("函数","一次函数"));
       //   getAllNodesHasRelation1("方程", "前置关系", true);   //获取节点指定关系的其他节点
      //  getAllNodesHasRelation2("圆");   //获取节点的所有相关节点
     //   getProperty("三角形");
    }

}
