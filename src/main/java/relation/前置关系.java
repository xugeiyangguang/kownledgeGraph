package relation;

import java.util.ArrayList;
import java.util.List;

public class 前置关系 {
    public static String getChineseName() {
        return "前置关系";
    }

    /**\
     * 关系的开始节点
     * @return 开始节点
     */
    public static List<String> getStartNodes() {
        List<String> startNodes = new ArrayList<>();
        startNodes.add("相似三角形");
        startNodes.add("全等三角形");
        startNodes.add("直角三角形");
        startNodes.add("直角三角形");
        startNodes.add("锐角三角形");
        startNodes.add("锐角三角形");
        startNodes.add("钝角三角形");
        startNodes.add("钝角三角形");
        startNodes.add("角平分线");
        startNodes.add("相似多边形");
        startNodes.add("正多边形的对称轴");
        startNodes.add("正多边形的对称轴");
        startNodes.add("相似多边形");
        startNodes.add("相似三角形");
        startNodes.add("全等三角形");
        startNodes.add("相似多边形");
        startNodes.add("位似图形");
        startNodes.add("勾股定理");
        startNodes.add("勾股数");
        return startNodes;
    }

    /**\
     * 关系的结束节点
     * @return 结束节点
     */
    public static List<String> getEndNodes() {
        List<String> endNodes = new ArrayList<>();
        endNodes.add("三角形");
        endNodes.add("三角形");
        endNodes.add("三角形");
        endNodes.add("直角");
        endNodes.add("锐角");
        endNodes.add("三角形");
        endNodes.add("三角形");
        endNodes.add("钝角");
        endNodes.add("角");
        endNodes.add("相似形");
        endNodes.add("正多边形");
        endNodes.add("对称轴");
        endNodes.add("多边形");
        endNodes.add("相似");
        endNodes.add("全等");
        endNodes.add("相似");
        endNodes.add("位似");
        endNodes.add("直角三角形");
        endNodes.add("勾股定理");

        return endNodes;
    }
}
