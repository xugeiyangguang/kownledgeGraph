package util;

import org.neo4j.driver.v1.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class CreateConceptKGUseCsv {
    private static final Logger LOG = LogManager.getLogger(GraphBaseUtils.class);

    /**
     * 创建单个csv文件的节点
     */
    public static void createNode(String path, String name) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///"+ path +name+ ".csv\" AS line\n" + "MERGE (p:" + name + "{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     *
     * @param path  包含所有CSV文件的路径
     */
    public static void createAllNode(String path){
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    System.out.println("处理文件:" + file2.getAbsolutePath());
                    //将知识点的所有名称加入到List中
                    String item[] = file2.getAbsolutePath().split("\\\\");
                    String tmp = item[item.length-1];
                    String tmpp = tmp.substring(0, tmp.length() - 4);
                    createNode(path,tmpp);
                    //   System.out.println("此文件包含知识点个数：" + size);
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void createRelationSub(String path) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///"+path+"子类关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.父类})\n" +
                    "MATCH (c:知识点 {name: row.子类})\n" +
                    "MERGE (e)-[r:有子类{name:\"有子类\"}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }
    public static void createRelationPre(String path) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///"+path+"前驱关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.当前知识点})\n" +
                    "MATCH (c:知识点 {name: row.前驱知识点})\n" +
                    "MERGE (e)-[r:前驱关系{name:\"有前驱\"}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    public static void createRelationAttribute(String path) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///"+path+"属性关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.实体})\n" +
                    "MATCH (c:知识点 {name: row.属性})\n" +
                    "MERGE (e)-[r:属性关系{name:\"有属性\"}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    public static void createRelationComposedOf(String path) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///"+path+"/组成关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.整体})\n" +
                    "MATCH (c:知识点 {name: row.部分})\n" +
                    "MERGE (e)-[r:组成关系{name:\"由组成\"}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    /**
     * 考点关系
     * @param path
     */
    public static void createRelationTest(String path) {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///"+path+"/考点关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.知识点})\n" +
                    "MATCH (c:知识点 {name: row.相关考点})\n" +
                    "MERGE (e)-[r:考点关系{name:\"有考点\"}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    public static void createAllRelation(String path) {
        createRelationSub(path);
        createRelationPre(path);
        createRelationAttribute(path);
        createRelationComposedOf(path);
        createRelationTest(path);
    }

    public static void setLabels(){
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "match(n) set n:知识点 return n";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }
    public static void deleteAllRelationAndNode() {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "MATCH (n)\n" +
                    "OPTIONAL MATCH (n)-[r]-()\n" +
                    "DELETE n,r";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    public static void main(String[] args) {
        String pathKownledge = "C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点/";
        String pathRelation = "C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点关系/";
       // deleteAllRelationAndNode();  //删除所有的知识点节点和关系
       // createNode(pathKownledge,"代数");
      //  createAllNode(pathKownledge);   //创建所有的知识点节点
      //  setLabels();     //增加所有节点的“知识点”标签
      //  createRelationSub(pathRelation);
       // createRelationPre(pathRelation);
        createAllRelation(pathRelation);   //创建所有的关系
    }

}
