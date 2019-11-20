package relation;

import java.util.ArrayList;
import java.util.List;

public class 包含关系 {
    public static String getChineseName() {
        return "包含关系";
    }

    /**\
     * 关系的开始节点
     * @return 开始节点
     */
    public static List<String> getStartNodes() {
        List<String> startNodes = new ArrayList<>();
        //  startNodes.add("三角形");
        startNodes.add("直线的方程");
        startNodes.add("直线的方程");
        startNodes.add("直线的方程");
        startNodes.add("直线的方程");
        startNodes.add("直线的方程");
        startNodes.add("圆的方程");
        startNodes.add("圆的方程");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("象限");
        startNodes.add("极坐标方程");
        startNodes.add("极坐标方程");
        startNodes.add("极坐标方程");

        return startNodes;
    }

    /**\
     * 关系的结束节点
     * @return 结束节点
     */
    public static List<String> getEndNodes() {
        List<String> endNodes = new ArrayList<>();
        //  endNodes.add("三角形的中线");
        endNodes.add("点斜式");
        endNodes.add("斜截式");
        endNodes.add("两点式");
        endNodes.add("截距式");
        endNodes.add("一般式");
        endNodes.add("圆的一般方程");
        endNodes.add("圆的标准方程");
        endNodes.add("第一象限");
        endNodes.add("第二象限");
        endNodes.add("第三象限");
        endNodes.add("第四象限");
        endNodes.add("第五象限");
        endNodes.add("第六象限");
        endNodes.add("第七象限");
        endNodes.add("第八象限");
        endNodes.add("曲线的极坐标方程");
        endNodes.add("直线的极坐标方程");
        endNodes.add("圆的极坐标方程");


        return endNodes;
    }
}
