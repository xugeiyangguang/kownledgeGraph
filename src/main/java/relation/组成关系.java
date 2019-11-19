package relation;

import java.util.ArrayList;
import java.util.List;

public class 组成关系 {
    public static String getChineseName() {
        return "组成关系";
    }

    /**\
     * 关系的开始节点
     * @return 开始节点
     */
    public static List<String> getStartNodes() {
        List<String> startNodes = new ArrayList<>();
        startNodes.add("弧");

        return startNodes;
    }

    /**\
     * 关系的结束节点
     * @return 结束节点
     */
    public static List<String> getEndNodes() {
        List<String> endNodes = new ArrayList<>();
        endNodes.add("圆");


        return endNodes;
    }
}
