package util;

import org.apache.commons.collections4.ListUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 将知识点文件进行同步 {
    //返回某一文件夹下的所有CSV文件内容
    //   static ArrayList<String> kownledgeNew = new ArrayList<>();   //分文件夹的知识点
    //   static ArrayList<String> kownledgeOld = new ArrayList<>();  //相似度使用的知识点
    public static HashMap<String, String> folderMethod2(String path) {
        File file = new File(path);
        ArrayList<String> re = new ArrayList<>();
        ArrayList<String> kownledgeNew = new ArrayList<>();  //记录多个文件的内容
        HashMap<String, String> labelMap = new HashMap<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    System.out.println("处理文件:" + file2.getAbsolutePath());
                    //将知识点的所有名称加入到List中
                    re = DealCSV1(file2.getAbsolutePath(), labelMap, kownledgeNew);
                    //   System.out.println("此文件包含知识点个数：" + size);
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return labelMap;
    }

    public static ArrayList<String> DealCSV1(String path, HashMap<String, String> labelMap, ArrayList<String> kownledge) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[0];//这就是你要的数据了
                if (!kownledge.contains(last)) {
                    kownledge.add(last);
                    //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                    System.out.print(last);
                    System.out.println("---" + item[1]);
                    labelMap.put(item[0], item[1]);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kownledge;
    }


    //返回单个CSV文件的内容
    public static void DealCSV(String path, HashMap<String, String> labelMap) {
        try {
            File csv = new File("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\带标签的知识点.csv"); // CSV数据文件

            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // 附加
            // 添加新的数据行

            BufferedReader reader = new BufferedReader(new FileReader(path));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[0];//这就是你要的数据了

                bw.write(item[0] + "," + labelMap.get(item[0]));
                bw.newLine();


            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }


    public static void main(String[] args) {
        HashMap<String, String> labelMap = folderMethod2("C:\\Users\\27124\\Desktop\\毕业论文\\课标知识点分类");
        DealCSV("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\初等数学知识点运行版.csv", labelMap);
        /*List<String> OldNotHave = ListUtils.subtract(New, Old);
        List<String> NewNotHave = ListUtils.subtract(Old, New);*/
        System.out.println();
    }

}
