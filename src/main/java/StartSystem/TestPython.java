package StartSystem;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestPython {

    public static void main(String[] args) {
        runCMD("cmd /c C:\\Users\\27124\\PycharmProjects\\Similarity\\script.bat");

//        // TODO Auto-generated method stub
//        Process proc;
//        try {
//         //   proc = Runtime.getRuntime().exec("python C:\\Users\\27124\\PycharmProjects\\Similarity\\dissertation\\SingleSentenceKownledge.py");// 执行py文件
//            proc = Runtime.getRuntime().exec("python C:\\Users\\27124\\PycharmProjects\\Similarity\\dissertation\\AllQuestionAndKownledge.py");// 执行py文件
//            //用输入输出流来截取结果
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            proc.waitFor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static ArrayList<String> runCMD(String cmd) {
        ArrayList<String> kownledge = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        Process child = null;
        int i = 1;
        try {
            child = runtime.exec(cmd);
            InputStream in = child.getInputStream();
            String output;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "gbk"));
            output = bufferedReader.readLine();
            while (output != null) {
                //由于在python中的处理结果会输出多行无用信息，因此在此只接收了必要的行信息
                if (i != 2) {
                    System.out.println(output);
                }
                output = bufferedReader.readLine();
                i++;

                if (i >= 7 && i <= 10) {
                    kownledge.add(output);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (child != null) {
                child.destroy();
            }
        }
        return kownledge;
    }

}
