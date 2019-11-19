package relation;

import java.util.ArrayList;
import java.util.List;

public class 属性关系 {
    public static String getChineseName() {
        return "属性关系";
    }

    /**\
     * 关系的开始节点
     * @return 开始节点
     */
    public static List<String> getStartNodes() {
        List<String> startNodes = new ArrayList<>();
        startNodes.add("三角形");
        startNodes.add("三角形");
        startNodes.add("三角形");
        startNodes.add("三角形");
        startNodes.add("相似多边形");
        startNodes.add("正多边形");
        startNodes.add("多边形");
        startNodes.add("多边形");
        startNodes.add("正方形");
        startNodes.add("正方形");
        startNodes.add("矩形");
        startNodes.add("矩形");
        startNodes.add("菱形");
        startNodes.add("梯形");
        startNodes.add("圆");
        startNodes.add("圆");
        startNodes.add("弧");
        startNodes.add("圆");
        startNodes.add("圆");
        startNodes.add("三角形");
        startNodes.add("轴对称图形");
        startNodes.add("线段");
        startNodes.add("线段");
        startNodes.add("向量");
        startNodes.add("椭圆");
        startNodes.add("椭圆");
        startNodes.add("双曲线");
        startNodes.add("双曲线");
        return startNodes;
    }

    /**\
     * 关系的结束节点
     * @return 结束节点
     */
    public static List<String> getEndNodes() {
        List<String> endNodes = new ArrayList<>();
        endNodes.add("三角形的中线");
        endNodes.add("三角形的高");
        endNodes.add("三角形的角平分线");
        endNodes.add("三角形的中位线");
        endNodes.add("相似比");
        endNodes.add("正多边形的对称轴");
        endNodes.add("多边形的内角");
        endNodes.add("多边形的外角");
        endNodes.add("正方形的面积");
        endNodes.add("正方形的体积");
        endNodes.add("矩形的面积");
        endNodes.add("矩形的周长");
        endNodes.add("菱形的面积");
        endNodes.add("梯形的面积");
        endNodes.add("直径");
        endNodes.add("半径");
        endNodes.add("圆心角");
        endNodes.add("弧");
        endNodes.add("切线");
        endNodes.add("三角形的内心");
        endNodes.add("对称轴");
        endNodes.add("线段的中点");
        endNodes.add("线段的垂直平分线");
        endNodes.add("向量的模");
        endNodes.add("椭圆的离心率");
        endNodes.add("椭圆的通径");
        endNodes.add("双曲线的通径");
        endNodes.add("双曲线的离心率");

        return endNodes;
    }
}
