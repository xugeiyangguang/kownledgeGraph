package util;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.logging.log4j.LogManager;
import org.neo4j.driver.v1.Transaction;

public class InitGraph {

    private static final Log4JLogger LOG = (Log4JLogger) LogManager.getLogger(InitGraph.class);
    ;

    public static void createNode() {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/代数.csv\" AS line\n" +
                    "MERGE (p:代数{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/几何.csv\" AS line\n" +
                    "MERGE (p:几何{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/概率.csv\" AS line\n" +
                    "MERGE (p:概率{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/计数原理.csv\" AS line\n" +
                    "MERGE (p:计数原理{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/简易逻辑.csv\" AS line\n" +
                    "MERGE (p:简易逻辑{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/矩阵与变换.csv\" AS line\n" +
                    "MERGE (p:矩阵与变换{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/算法初步.csv\" AS line\n" +
                    "MERGE (p:算法初步{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/统计.csv\" AS line\n" +
                    "MERGE (p:统计{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS  FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/推理与证明.csv\" AS line\n" +
                    "MERGE (p:推理与证明{name:line.知识点名称})";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    public static void createRelation() {
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点关系/属性关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.实体})\n" +
                    "MATCH (c:知识点 {name: row.属性})\n" +
                    "MERGE (e)-[r:有属性{name:'有属性'}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点关系/组成关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.整体})\n" +
                    "MATCH (c:知识点 {name: row.部分})\n" +
                    "MERGE (e)-[r:有部分{name:'有部分'}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点关系/子类关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.父类})\n" +
                    "MATCH (c:知识点 {name: row.子类})\n" +
                    "MERGE (e)-[r:有子类{name:'有子类'}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点关系/前驱关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.当前知识点})\n" +
                    "MATCH (c:知识点 {name: row.前驱知识点})\n" +
                    "MERGE (e)-[r:有前驱{name:'有前驱'}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
        try (Transaction tx = DriverSingleton.getInstance().session().beginTransaction()) {
            String cypher = "LOAD CSV WITH HEADERS FROM \"file:///C:/Users/27124/Desktop/毕业论文/dissertation/分类知识点及其关系/知识点及其关系/知识点关系/考点关系.csv\" AS row\n" +
                    "MATCH (e:知识点 {name: row.知识点})\n" +
                    "MATCH (c:知识点 {name: row.相关考点})\n" +
                    "MERGE (e)-[r:有考点{name:'有考点'}]->(c)";
            LOG.debug(cypher);
            tx.run(cypher);
            tx.success();
        }
    }

    public static void main(String[] args) {
        createNode();
        createRelation();
    }
}
