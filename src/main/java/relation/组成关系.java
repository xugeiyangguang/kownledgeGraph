package relation;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*什么组成给了什么*/
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
        startNodes.add("弧");
        startNodes.add("平面");
        startNodes.add("角");
        startNodes.add("实部");
        startNodes.add("虚部");

        return startNodes;
    }

    /**\
     * 关系的结束节点
     * @return 结束节点
     */
    public static List<String> getEndNodes() {
        List<String> endNodes = new ArrayList<>();
        endNodes.add("圆");
        endNodes.add("扇形");
        endNodes.add("二面角");
        endNodes.add("二面角");
        endNodes.add("复数");
        endNodes.add("复数");


        return endNodes;
    }

    public static void main(String[] args) {
        List<String> startNode = getStartNodes();
        List<String> endNode = getEndNodes();

        for (String s : startNode) {

            try {
                //在这修改处理结果的路径
                OutputStream os = new FileOutputStream("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\组成关系1.txt", true);
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
                OutputStream os = new FileOutputStream("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\组成关系2.txt", true);
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
