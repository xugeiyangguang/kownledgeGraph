package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 将类名导出到txt文件中 {
    public static void main(String[] args) {
        List<String> re = getAllClassesAndInterfacesName("C:\\Users\\27124\\IdeaProjects\\graphknowledge_xy\\src\\main\\java\\data");
        postDeal(re);
    }
    public static List<String> getAllClassesAndInterfacesName (String path) {
        File file = new File(path);
        List<String> nameResults = new ArrayList<>();
        if (file.exists()) {
            List<File> list = new LinkedList<>();

            List<File> fileResults = new ArrayList<>();
            File[] files = file.listFiles();

            for (File tempFile : files) {
                if (tempFile.isDirectory()) {
                    list.add(tempFile);
                }
                else {
                    fileResults.add(tempFile);
                }
            }

            File tempFiles;
            while (!list.isEmpty()) {
                tempFiles = list.remove(0);
                files = tempFiles.listFiles();
                for (File tempFile : files) {
                    if (tempFile.isDirectory()) {
                        list.add(tempFile);
                    }
                    else {
                        fileResults.add(tempFile);
                    }
                }
            }
            fileResults.forEach(result -> {
                nameResults.add(result.getPath().replace(path + "\\", "").replace("\\", ".").replace(".java", ""));
            });
        }
        return nameResults;
    }

    public static void postDeal(List<String> strings) {
        for (String s : strings) {
            String[] ss = s.split("\\.");
            String path = ss[0] + ".txt";

            try {
                //在这修改处理结果的路径
                OutputStream os = new FileOutputStream("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\" + path, true);
                PrintWriter pw=new PrintWriter(os);

                pw.println(ss[1]);

                pw.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


