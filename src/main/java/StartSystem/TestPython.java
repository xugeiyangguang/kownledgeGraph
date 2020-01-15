package StartSystem;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public static void runCMD(String cmd) {
        Runtime runtime = Runtime.getRuntime();
        Process child = null;
        int i = 1;
        try {
            child = runtime.exec(cmd);
            InputStream in = child.getInputStream();
            String output = null;
            int isSuccessful = 0;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "gbk"));

            output = bufferedReader.readLine();
            while (output != null) {
                if(i!=2) {
                    System.out.println(output);
                }
                output = bufferedReader.readLine();
                i++;
            }
            try {
                //isSuccessful = child.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (child != null) {
                child.destroy();
            }
        }
    }

}
