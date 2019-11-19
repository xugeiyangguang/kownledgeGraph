package util;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

/**
 * @author cyq7on
 * @description neo4j驱动单例
 * @create 2019/7/10
 **/
public class DriverSingleton {

    /**
     * 私有构造方法，禁止在其他类中创建实例
     */
    private DriverSingleton() {
    }

    /**
     * 获取实例
     */
    public static Driver getInstance() {
        return StaticSingletonHolder.instance;
    }

    /**
     * 一个私有的静态内部类，用于初始化一个静态final实例
     */
    private static class StaticSingletonHolder {
        //连接的是201邓力华位置上的neo4j
        //private static final Driver instance = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "hcj"));
        //连接的是201服务器上的neo4j
        //private static final Driver instance = GraphDatabase.driver("bolt://192.168.1.70:7687", AuthTokens.basic("neo4j", "ad201"));
        private static final Driver instance = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "825402"));
    }

}
