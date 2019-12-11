package relation;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        startNodes.add("三视图");
        startNodes.add("三视图");
        startNodes.add("三视图");
        startNodes.add("直线与平面的关系");
        startNodes.add("直线与平面的关系");
        startNodes.add("直线与平面的关系");
        startNodes.add("两平面的位置关系");
        startNodes.add("两平面的位置关系");
        startNodes.add("两平面的位置关系");
        startNodes.add("代数式");
        startNodes.add("代数式");
        startNodes.add("代数式");
        startNodes.add("方程");
        startNodes.add("方程");
        startNodes.add("方程");
        startNodes.add("方程");
        startNodes.add("集合的表示法");
        startNodes.add("集合的表示法");
        startNodes.add("集合的表示法");
        startNodes.add("集合的分类");
        startNodes.add("集合的分类");
        startNodes.add("集合的分类");
        startNodes.add("集合的分类");
        startNodes.add("常用的数集符号");
        startNodes.add("常用的数集符号");
        startNodes.add("常用的数集符号");
        startNodes.add("常用的数集符号");
        startNodes.add("常用的数集符号");
        startNodes.add("常用的数集符号");
        startNodes.add("集合之间的关系");
        startNodes.add("集合之间的关系");
        startNodes.add("集合之间的关系");
        startNodes.add("集合之间的关系");
        startNodes.add("集合之间的关系");
        startNodes.add("集合之间的关系");
        startNodes.add("集合的分类");
        startNodes.add("集合的分类");
        startNodes.add("集合的分类");
        startNodes.add("集合的分类");
        startNodes.add("区间");
        startNodes.add("区间");
        startNodes.add("区间");
        startNodes.add("函数");
        startNodes.add("函数");
        startNodes.add("函数的表示");
        startNodes.add("函数的表示");
        startNodes.add("函数的表示");
        startNodes.add("函数");
        startNodes.add("函数");
        startNodes.add("函数的奇偶性");
        startNodes.add("函数的奇偶性");
        startNodes.add("函数图像变化的四种形式");
        startNodes.add("函数图像变化的四种形式");
        startNodes.add("函数图像变化的四种形式");
        startNodes.add("函数图像变化的四种形式");
        startNodes.add("方根");
        startNodes.add("方根");
        startNodes.add("函数");
        startNodes.add("函数");
        startNodes.add("函数");
        startNodes.add("函数");
        startNodes.add("函数");
        startNodes.add("一次函数");
        startNodes.add("函数");
        startNodes.add("二次函数");
        startNodes.add("函数");
        startNodes.add("反比例函数");
        startNodes.add("角");
        startNodes.add("角");
        startNodes.add("角");
        startNodes.add("角的单位制");
        startNodes.add("角的单位制");
        startNodes.add("象限角的表示");
        startNodes.add("象限角的表示");
        startNodes.add("象限角的表示");
        startNodes.add("象限角的表示");
        startNodes.add("三角函数的分类");
        startNodes.add("三角函数的分类");
        startNodes.add("三角函数的分类");
        startNodes.add("三角函数线");
        startNodes.add("三角函数线");
        startNodes.add("三角函数线");
        startNodes.add("特殊角的三角函数值");
        startNodes.add("特殊角的三角函数值");
        startNodes.add("特殊角的三角函数值");
        startNodes.add("数列的表示法");
        startNodes.add("数列的表示法");
        startNodes.add("数列的表示法");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列的分类");
        startNodes.add("数列");
        startNodes.add("数列");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("不等式");
        startNodes.add("算法初步");
        startNodes.add("算法初步");
        startNodes.add("算法初步");
        startNodes.add("算法初步");
        startNodes.add("算法初步");
        startNodes.add("算法初步");
        startNodes.add("算法初步");
        startNodes.add("随机抽样");
        startNodes.add("随机抽样");
        startNodes.add("随机抽样");
        startNodes.add("用样本估计总体");
        startNodes.add("用样本估计总体");
        startNodes.add("用样本估计总体");
        startNodes.add("用样本估计总体");
        startNodes.add("用样本估计总体");
        startNodes.add("用样本估计总体");
        startNodes.add("样本的数字特征");
        startNodes.add("样本的数字特征");
        startNodes.add("样本的数字特征");
        startNodes.add("样本的数字特征");
        startNodes.add("样本的数字特征");
        startNodes.add("样本的数字特征");
        startNodes.add("变量间的相关关系");
        startNodes.add("变量间的相关关系");
        startNodes.add("变量间的相关关系");
        startNodes.add("变量间的相关关系");
        startNodes.add("两个变量的线性相关");
        startNodes.add("两个变量的线性相关");
        startNodes.add("两个变量的线性相关");
        startNodes.add("两个变量的线性相关");
        startNodes.add("回归直线方程");
        startNodes.add("回归直线方程");
        startNodes.add("回归直线方程");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("数据的描述");
        startNodes.add("统计");
        startNodes.add("统计");
        startNodes.add("统计");
        startNodes.add("统计");
        startNodes.add("事件");
        startNodes.add("事件");
        startNodes.add("事件");
        startNodes.add("事件");
        startNodes.add("随机现象");
        startNodes.add("概率的计算方法");
        startNodes.add("概率的计算方法");
        startNodes.add("概率的计算方法");
        startNodes.add("概率的计算方法");
        startNodes.add("概率的计算方法");
        startNodes.add("事件的关系");
        startNodes.add("事件的关系");
        startNodes.add("事件的关系");
        startNodes.add("事件的关系");
        startNodes.add("事件的关系");
        startNodes.add("事件的关系");
        startNodes.add("古典概型");
        startNodes.add("古典概型");
        startNodes.add("随机变量及其分布");
        startNodes.add("随机变量及其分布");
        startNodes.add("离散型随机变量");
        startNodes.add("离散型随机变量");
        startNodes.add("离散型随机变量");
        startNodes.add("计数原理");
        startNodes.add("计数原理");
        startNodes.add("计数原理");
        startNodes.add("计数原理");
        startNodes.add("计数原理");
        startNodes.add("命题");
        startNodes.add("命题");
        startNodes.add("命题");
        startNodes.add("命题");
        startNodes.add("命题");
        startNodes.add("命题");
        startNodes.add("充分条件与必要条件");
        startNodes.add("充分条件与必要条件");
        startNodes.add("充分条件与必要条件");
        startNodes.add("充分条件与必要条件");
        startNodes.add("充分条件与必要条件");
        startNodes.add("充分条件与必要条件");
        startNodes.add("逻辑联结词");
        startNodes.add("逻辑联结词");
        startNodes.add("逻辑联结词");
        startNodes.add("推理与证明");
        startNodes.add("推理与证明");
        startNodes.add("推理");
        startNodes.add("推理");
        startNodes.add("推理");
        startNodes.add("推理");
        startNodes.add("证明");
        startNodes.add("证明");
        startNodes.add("证明的方法");
        startNodes.add("证明的方法");
        startNodes.add("证明的方法");
        startNodes.add("证明的方法");
        startNodes.add("证明的方法");
        startNodes.add("证明的方法");
        startNodes.add("矩阵与变换");
        startNodes.add("矩阵与变换");
        startNodes.add("矩阵与变换");
        startNodes.add("矩阵");
        startNodes.add("矩阵");
        startNodes.add("矩阵");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");
        startNodes.add("变换");


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
        endNodes.add("主视图");
        endNodes.add("侧视图");
        endNodes.add("俯视图");
        endNodes.add("直线与平面垂直");
        endNodes.add("直线与平面平行");
        endNodes.add("直线与平面相交");
        endNodes.add("两平面相交");
        endNodes.add("两平面平行");
        endNodes.add("两平面垂直");
        endNodes.add("整式");
        endNodes.add("分式");
        endNodes.add("二次根式");
        endNodes.add("一元一次方程");
        endNodes.add("一元二次方程");
        endNodes.add("二元一次方程");
        endNodes.add("二元一次方程组");
        endNodes.add("列举法");
        endNodes.add("描述法");
        endNodes.add("图示法");
        endNodes.add("有限集");
        endNodes.add("无限集");
        endNodes.add("数集");
        endNodes.add("点集");
        endNodes.add("非负整数集");
        endNodes.add("正整数集");
        endNodes.add("整数集");
        endNodes.add("有理数集");
        endNodes.add("实数集");
        endNodes.add("复数集合");
        endNodes.add("子集");
        endNodes.add("真子集");
        endNodes.add("集合相等");
        endNodes.add("交集");
        endNodes.add("并集");
        endNodes.add("补集");
        endNodes.add("全集");
        endNodes.add("空集");
        endNodes.add("偶数集");
        endNodes.add("奇数集");
        endNodes.add("闭区间");
        endNodes.add("开区间");
        endNodes.add("半开半闭区间");
        endNodes.add("复合函数");
        endNodes.add("分段函数");
        endNodes.add("解析法");
        endNodes.add("列表法");
        endNodes.add("图像法");
        endNodes.add("增函数");
        endNodes.add("减函数");
        endNodes.add("奇函数");
        endNodes.add("偶函数");
        endNodes.add("平移变换");
        endNodes.add("翻折变换");
        endNodes.add("伸缩变换");
        endNodes.add("对称变换");
        endNodes.add("平方根");
        endNodes.add("立方根");
        endNodes.add("指数函数");
        endNodes.add("对数函数");
        endNodes.add("反函数");
        endNodes.add("幂函数");
        endNodes.add("一次函数");
        endNodes.add("正比例函数");
        endNodes.add("二次函数");
        endNodes.add("抛物线");
        endNodes.add("反比例函数");
        endNodes.add("双曲线");
        endNodes.add("正角");
        endNodes.add("负角");
        endNodes.add("零角");
        endNodes.add("角度制");
        endNodes.add("弧度制");
        endNodes.add("第一象限角");
        endNodes.add("第二象限角");
        endNodes.add("第三象限角");
        endNodes.add("第四象限角");
        endNodes.add("正弦");
        endNodes.add("余弦");
        endNodes.add("正切");
        endNodes.add("正弦线");
        endNodes.add("余弦线");
        endNodes.add("正切线");
        endNodes.add("正弦值");
        endNodes.add("余弦值");
        endNodes.add("正切值");
        endNodes.add("通项公式法表示数列");
        endNodes.add("图像法表示数列");
        endNodes.add("列表法表示数列");
        endNodes.add("有穷数列");
        endNodes.add("无穷数列");
        endNodes.add("递增数列");
        endNodes.add("递减数列");
        endNodes.add("常数列");
        endNodes.add("摆动数列");
        endNodes.add("周期数列");
        endNodes.add("有界数列");
        endNodes.add("无界数列");
        endNodes.add("等差数列");
        endNodes.add("等比数列");
        endNodes.add("一元一次不等式");
        endNodes.add("一元一次不等式组");
        endNodes.add("一元二次不等式");
        endNodes.add("二元一次不等式");
        endNodes.add("基本不等式");
        endNodes.add("几何平均不等式");
        endNodes.add("绝对值不等式");
        endNodes.add("贝努利不等式");
        endNodes.add("柯西不等式");
        endNodes.add("排序不等式");
        endNodes.add("程序框图");
        endNodes.add("辗转相除法");
        endNodes.add("更相减损术");
        endNodes.add("秦九韶算法");
        endNodes.add("进位制");
        endNodes.add("流程图");
        endNodes.add("结构图");
        endNodes.add("简单随机抽样");
        endNodes.add("系统抽样");
        endNodes.add("分层抽样");
        endNodes.add("频率分布表");
        endNodes.add("频率分布直方图");
        endNodes.add("频率分布折线图");
        endNodes.add("茎叶图");
        endNodes.add("总体密度曲线");
        endNodes.add("样本的数字特征");
        endNodes.add("众数");
        endNodes.add("中位数");
        endNodes.add("平均数");
        endNodes.add("样本标准差");
        endNodes.add("样本方差");
        endNodes.add("极差");
        endNodes.add("相关关系");
        endNodes.add("两个变量的线性相关");
        endNodes.add("回归直线方程");
        endNodes.add("非线性回归");
        endNodes.add("回归分析");
        endNodes.add("散点图");
        endNodes.add("正相关");
        endNodes.add("负相关");
        endNodes.add("最小二乘法");
        endNodes.add("线性相关系数");
        endNodes.add("相关性检验");
        endNodes.add("频数");
        endNodes.add("频率");
        endNodes.add("组数");
        endNodes.add("组距");
        endNodes.add("频数分布表");
        endNodes.add("条形统计图");
        endNodes.add("扇形统计图");
        endNodes.add("折线统计图");
        endNodes.add("频数分布直方图");
        endNodes.add("频数折线图");
        endNodes.add("数据的描述");
        endNodes.add("变量间的相关关系");
        endNodes.add("用样本估计总体");
        endNodes.add("随机抽样");
        endNodes.add("随机事件");
        endNodes.add("确定事件");
        endNodes.add("不可能事件");
        endNodes.add("必然事件");
        endNodes.add("确定性现象或必然现象");
        endNodes.add("列举法");
        endNodes.add("画树状图法");
        endNodes.add("频率估计概率");
        endNodes.add("模拟实验");
        endNodes.add("列表法");
        endNodes.add("包含关系");
        endNodes.add("相等关系");
        endNodes.add("并事件");
        endNodes.add("交事件");
        endNodes.add("互斥事件");
        endNodes.add("对立事件");
        endNodes.add("等可能基本事件");
        endNodes.add("基本事件");
        endNodes.add("二项式分布及其应用");
        endNodes.add("离散型随机变量");
        endNodes.add("正态分布");
        endNodes.add("两点分布");
        endNodes.add("超几何分布");
        endNodes.add("分类加法计数原理");
        endNodes.add("分布乘法计数原理");
        endNodes.add("排列");
        endNodes.add("组合");
        endNodes.add("二项式定理");
        endNodes.add("原命题");
        endNodes.add("逆命题");
        endNodes.add("否命题");
        endNodes.add("逆否命题");
        endNodes.add("真命题");
        endNodes.add("假命题");
        endNodes.add("充分条件");
        endNodes.add("必要条件");
        endNodes.add("充分而不必要条件");
        endNodes.add("必要而不充分条件");
        endNodes.add("充要条件");
        endNodes.add("既不充分也不必要条件");
        endNodes.add("或");
        endNodes.add("非");
        endNodes.add("且");
        endNodes.add("推理");
        endNodes.add("证明");
        endNodes.add("归纳推理");
        endNodes.add("类比推理");
        endNodes.add("合情推理");
        endNodes.add("演绎推理");
        endNodes.add("直接证明");
        endNodes.add("间接证明");
        endNodes.add("综合法");
        endNodes.add("分析法");
        endNodes.add("反证法");
        endNodes.add("分析综合法");
        endNodes.add("数学归纳法");
        endNodes.add("面积法");
        endNodes.add("矩阵");
        endNodes.add("变换");
        endNodes.add("二阶行列式");
        endNodes.add("二阶矩阵");
        endNodes.add("零矩阵");
        endNodes.add("逆矩阵");
        endNodes.add("逆变换");
        endNodes.add("复合变换");
        endNodes.add("矩阵相等");
        endNodes.add("变换相等");
        endNodes.add("切变变换");
        endNodes.add("投影变换");
        endNodes.add("伸缩变换");
        endNodes.add("反射变换");
        endNodes.add("旋转变换");
        endNodes.add("线性变换");


        return endNodes;
    }

    public static void main(String[] args) {
        List<String> startNode = getStartNodes();
        List<String> endNode = getEndNodes();

        for (String s : startNode) {

            try {
                //在这修改处理结果的路径
                OutputStream os = new FileOutputStream("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\包含关系1.txt", true);
                PrintWriter pw=new PrintWriter(os);

                pw.println(s);

                pw.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (String s : endNode) {

            try {
                //在这修改处理结果的路径
                OutputStream os = new FileOutputStream("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\包含关系2.txt", true);
                PrintWriter pw=new PrintWriter(os);

                pw.println(s);

                pw.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
