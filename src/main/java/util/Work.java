package util;

import java.io.*;
import java.util.*;

public class Work {
    static long startTime; //获取开始时间
    public ArrayList<Map.Entry<String, Integer>> initDB(ArrayList<ArrayList<String>> startDB) {
        HashMap<String, Integer> map = new HashMap<>(); //统计出现得次数
        //  在这修改support
        int support = 2;
        //读取文件
        List<String> strings = new ArrayList<>();
        BufferedReader br;
        try {
            //在这修改原数据的路径
            Reader reader = new FileReader("C:\\Users\\27124\\Desktop\\原数据.txt");
            br = new BufferedReader(reader);
            String line;
            while(null != (line = br.readLine())){
                strings.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < strings.size(); i++) {
            ArrayList<String> tmp = new ArrayList<>();
            String[] ss = strings.get(i).split(" ");
            for (int j = 0; j < ss.length; j++) {
                String a = ss[j];
                tmp.add(a);
                if (map.containsKey(a)) {
                    map.put(a, map.get(a) + 1);
                } else {
                    map.put(a, 1);
                }
            }
            startDB.add(tmp);
        }
        //-----------------------------------------------------
        System.out.println("原始数据为：");
        OutputDB(startDB);


        //删除DB中<support的项
        for (int i = 0; i < startDB.size(); i++) {
            for (int j = 0; j < startDB.get(i).size(); j++) {
                if (map.get(startDB.get(i).get(j)) < support) {
                    startDB.get(i).remove(startDB.get(i).get(j));
                }
            }
        }

        System.out.println("删除少数项后：");
        OutputDB(startDB);

        //按照出现的频率对startDB排序
        startTime=System.currentTimeMillis();
        for (int i = 0; i < startDB.size(); i++) {
            Collections.sort(startDB.get(i), new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    if (map.get(s1) - map.get(s2) == 0) {
                        return s1.compareTo(s2);
                    } else {
                        return map.get(s2) - map.get(s1);
                    }
                //    return s1.compareTo(s2);
                }
            });
        }

        System.out.println("startDB按照字符出现频率进行排序后：");
        OutputDB(startDB);
        //删除表中<support的项，得到TB_H，对项头表排序，一个链表里面是键值对
        ArrayList<Map.Entry<String, Integer>> TB_H = new ArrayList<>(map.entrySet());
        Collections.sort(TB_H, (o1, o2) -> {
            if (o2.getValue() - o1.getValue() == 0) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return o2.getValue() - o1.getValue();
            }
        });
        Iterator<Map.Entry<String, Integer>> iterator = TB_H.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> item = iterator.next();
            if (item.getValue() < support) {
                iterator.remove();
            }
        }

        System.out.println("原始Map:" + map);
        System.out.println("TB_H: " + TB_H);  //链表里面加的键值对
        return TB_H;

    }

    public void OutputDB(ArrayList<ArrayList<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("(" + i + ") " + list.get(i));
        }
    }

    public void hmfiCore(ArrayList<ArrayList<String>> startDB,
                         ArrayList<Map.Entry<String, Integer>> TB_H,
                         ArrayList<ArrayList<String>> re,   //存储最终的结果
                         ArrayList<String> str,   //保存目前的项表头内容
                         HashMap<Integer, Integer> pos   // 保存DB每条数据当前的指针位置
                       //  HashSet<String> ignore    //代表需要被忽略的字符
                         ) {   //保存项表头目前该处理的位置

        for (int cur = 0; cur <= TB_H.size(); ++cur) {
         //   outputDB(startDB, pos);
            if (cur == TB_H.size()) {
                ArrayList<String> tmp = new ArrayList<>();
                for (String i : str) {
                    tmp.add(i);
                }
                re.add(tmp);
                if (str.size() >= 1) {
                    str.remove(str.size() - 1);
                }

                return;
            }

            //是0或者1不递归

            if (TB_H.get(cur).getValue() == 1 || TB_H.get(cur).getValue() == 0) {
            //    ++cur;
                continue;
            }

            String curValue = TB_H.get(cur).getKey();
            ArrayList<String> ignore = new ArrayList<>();
            ignore.add(curValue);  //将表头加入忽略集合

            //对指针表修改
            HashMap<Integer, Integer> pos1 = new HashMap<>();
            for (int i : pos.keySet()) {
                if (pos.get(i) >= str.size() - 1 && startDB.get(i).contains(curValue)) {
                    int index = startDB.get(i).indexOf(curValue);
                    pos1.put(i, index);
                } else {
                    pos1.put(i, pos.get(i));
                }
            }

            System.out.println("---------------------------------------");
            System.out.println("指针表（标识每条数据当前指针的位置）：");
            System.out.println(pos1);

            //创建新的map
            ArrayList<Map.Entry<String, Integer>> TB_H1 = new ArrayList<>();
            int q = 0;
            for (int k = cur + 1; k < TB_H.size(); k++) {
                HashMap<String, Integer> tmp = new HashMap<>();
                tmp.put(TB_H.get(k).getKey(), 0);
                TB_H1.add(tmp.entrySet().iterator().next());
               // TB_H1.get(q++).setValue(0);
            }

            //更新TB_H表cur
            for (int i : pos1.keySet()) {
                if (pos1.get(i) >= 0 && startDB.get(i).get(pos1.get(i)).equals(curValue)) {
                    for (int j = pos1.get(i) + 1; j < startDB.get(i).size(); j++) {
                        for (Map.Entry<String, Integer> k : TB_H1) {
                            if (k.getKey().equals(startDB.get(i).get(j)) ) {
                                int flag = 0;
                                for (int m = 0; m < cur; m++) {
                                    if (k.getKey().equals(TB_H.get(m).getKey())) {
                                        flag = 1;
                                    }
                                }
                                if (flag == 0) {
                                    k.setValue(k.getValue() + 1);
                                }

                            }
                        }
                    }
                }
            }
            str.add(curValue);
            System.out.println("TB_H项头表：" + str);
            System.out.println(TB_H1);


            hmfiCore(startDB, TB_H1, re, str, pos1);

        }
    }


    //初始化指针表
    public void initPos(ArrayList<ArrayList<String>> startDB, HashMap<Integer, Integer> pos) {
        for (int i = 0; i < startDB.size(); i++) {
            pos.put(i, -1);
        }
    }

    //输出指针表的内容
    public void outputPos(HashMap<Integer, Integer> pos) {
        System.out.println("指针表的内容：");
        for (int i : pos.keySet()) {
            System.out.print(i+"  |");
        }
        System.out.println();
        System.out.println("-----------------------------");
        for (int i : pos.values()) {
            System.out.print(i+"  |");
        }
    }

    public void outputDB(ArrayList<ArrayList<String>> startDB, HashMap<Integer, Integer> pos) {
        System.out.println("--------目前DB内容----------");
        for (int i = 0; i < pos.size(); i++) {
            if (pos.get(i) >= 0) {
                ArrayList<String> tmp = startDB.get(i);
                for (int j = pos.get(i); j < tmp.size(); j++) {
                    System.out.print(tmp.get(j) + " ");
                }
                System.out.println();
            }

        }
        System.out.println("---------------------------");

    }

    //删除子集
    public void dealResult(ArrayList<ArrayList<String>> re) {
        ArrayList<ArrayList<String>> delete = new ArrayList<>();
        for (int i = 0; i < re.size(); i++) {
            if (re.get(i).size() <= 1||delete.contains(re.get(i))) {
                continue;
            }
            for (int j = i + 1; j < re.size(); j++) {
                if (delete.contains(re.get(j))) {
                    continue;
                }
                if (delete.contains(re.get(i))) {
                    break;
                }
                int k = 0;
                if (re.get(i).size() > re.get(j).size()) {

                    for (; k < re.get(j).size() && re.get(i).contains(re.get(j).get(k)); k++) { }
                    if (k == re.get(j).size()) {
                        delete.add(re.get(j));
                    }
                } else if (re.get(i).size() < re.get(j).size()) {
                    for (; k < re.get(i).size() && re.get(j).contains(re.get(i).get(k)); k++) { }
                    if (k == re.get(i).size()) {
                        delete.add(re.get(i));
                    }
                }

            }

        }
        Iterator<ArrayList<String>> iterator = re.iterator();
        while (iterator.hasNext()) {
            ArrayList<String> tmp = iterator.next();
            if (delete.contains(tmp)) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        Work w = new Work();
        ArrayList<ArrayList<String>> startDB = new ArrayList<>();
        ArrayList<Map.Entry<String, Integer>> TB_H = w.initDB(startDB);
        ArrayList<ArrayList<String>> re = new ArrayList<>();  //存储最终的结果
        ArrayList<String> str = new ArrayList<>();  //保存目前的项表头内容
        HashMap<Integer, Integer> pos = new HashMap<>(); // 保存DB每条数据当前的指针位置
        HashSet<String> ignore = new HashSet<>();  //代表需要被忽略的字符
        int cur = 0;   //保存项表头目前该处理的位置

        w.initPos(startDB, pos);
      //  w.outputPos(pos);



        w.hmfiCore(startDB, TB_H, re, str, pos);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("-------------------处理结果--------------------");
        System.out.println(re);
        w.dealResult(re);
        System.out.println("----------------去除重复子集和单项---------------");
        System.out.println(re);
        //将结果写入文件
        try {
            //在这修改处理结果的路径
            OutputStream os = new FileOutputStream("C:\\Users\\27124\\Desktop\\处理结果.txt");
            PrintWriter pw=new PrintWriter(os);

            for (ArrayList<String> s : re) {
                pw.println(s);
            }
            pw.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}
