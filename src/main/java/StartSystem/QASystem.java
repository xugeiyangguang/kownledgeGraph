package StartSystem;

import util.GraphBaseUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class QASystem {
    static Scanner scanner = new Scanner(System.in);

    /**
     * 完成一号服务：输入题目，返回题目的知识点   OK
     */
    public static void one() {
        System.out.println("请输入一道数学题目：");
        String question = scanner.nextLine();

        try {
            //在这修改处理结果的路径
            OutputStream os = new FileOutputStream("C:\\Users\\27124\\PycharmProjects\\Similarity\\dissertation\\test.txt");
            PrintWriter pw = new PrintWriter(os);
            pw.println(question);
            pw.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestPython.runCMD("cmd /c C:\\Users\\27124\\PycharmProjects\\Similarity\\script.bat");

    }

    /**
     * 完成2号任务：输入数学知识点，返回知识点相关的题目   OK
     */
    public static void two() {
        System.out.println("请输入一个知识点：");
        String kownledge = scanner.nextLine();
        HashSet<String> kownSet = new HashSet<>();
        kownSet.add(kownledge);
        //集合中存放的是需要查找的无重复的知识点
        HashSet<String> kownledges = GraphBaseUtils.getAllNodesHasRelation2(kownledge, true);   //获取节点的所有相关节点
        if (kownledges != null) {
            for (String i : kownledges) {
                kownSet.add(i);
            }
        }
        System.out.println(kownSet);
        HashMap<String, String> map = new HashMap<>();
        HashSet<String> questionSet = new HashSet<>();
        System.out.println("=================相似题目推荐============");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\知识点汇总1.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if (item.length != 5) {
                    continue;
                }
                for (int i = item.length - 3; i < item.length-1; i++) {
                    if (kownSet.contains(item[i])) {
                        //    map.put(item[2], item[0]);
                     //   System.out.println(item[0]);
                        questionSet.add(item[0]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String i : questionSet) {
            System.out.println(i);
        }
    }

    /**
     * 完成3号任务：输入一道数学题目，返回相关的数学题目
     */
    public static void three() {
        System.out.println("请输入一道数学题目：");
        String question = scanner.nextLine();

        try {
            //在这修改处理结果的路径
            OutputStream os = new FileOutputStream("C:\\Users\\27124\\PycharmProjects\\Similarity\\dissertation\\test.txt");
            PrintWriter pw = new PrintWriter(os);
            pw.println(question);
            pw.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> kownledgeSimilar = TestPython.runCMD("cmd /c C:\\Users\\27124\\PycharmProjects\\Similarity\\script.bat");
        ArrayList<String> kownledge = new ArrayList<>();
        for (String i : kownledgeSimilar) {
            String[] tmp = i.split("'");
            kownledge.add(tmp[1]);
        }
        System.out.println(kownledge);

        HashSet<String> questionSet = new HashSet<>();
        System.out.println("=================相似题目推荐============");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\知识点汇总1.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if (item.length != 5) {
                    continue;
                }
                for (int i = item.length - 3; i < item.length-1; i++) {
                    if (kownledge.contains(item[i])) {
                        //    map.put(item[2], item[0]);
                        questionSet.add(item[0]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String i : questionSet) {
            System.out.println(i);
        }
    }

    /**
     * 完成4号任务：输入一个知识点，返回该知识点的局部网络   OK
     */
    public static void four() {
        System.out.println("请输入一个知识点：");
        String kownledge = scanner.nextLine();
        GraphBaseUtils.getAllNodesHasRelation2(kownledge, true);   //获取节点的所有相关节点
    }

    public static void main(String[] args) {
        do {
            System.out.println("================================================");
            System.out.println("欢迎使用智能化教学系统，本系统提供以下几种服务：");
            System.out.println("1. 抽取题目的相关考点");
            System.out.println("2. 返回相关考点的数学题目");
            System.out.println("3. 返回相似题目");
            System.out.println("4. 返回知识点的局部关系图");
            System.out.print("请输入服务编号：");

            int num = scanner.nextInt();
            scanner.nextLine();
            System.out.println("================================================");
            if (num == 1) {
                one();
            } else if (num == 2) {
                two();
            } else if (num == 3) {
                three();
            } else if (num == 4) {
                four();
            } else if (num == 0) {
                System.out.println("退出系统！");
                return;
            } else {
                System.out.println("请重新输入选择服务：");
            }
        } while (true);

    }
}
