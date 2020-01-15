package util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.charset.Charset;


public class Main {

    public static void main(String[] args) throws Exception{
        //需写入的csv文件
        String src = "C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\知识点及其关系\\知识点\\代数.csv";
        //读
        CsvReader r = new CsvReader("C:\\Users\\27124\\Desktop\\毕业论文\\dissertation\\分类知识点及其关系\\知识点及其关系\\知识点\\输出测试.csv", ',', Charset.forName("GBK"));
        //写
        byte[] uft8bom = {(byte)0xef,(byte)0xbb,(byte)0xbf};
        FileOutputStream fos = new FileOutputStream(new File(src));
        fos.write(uft8bom);
        fos.close();
        File f = new File(src);
        BufferedWriter writer = new BufferedWriter(new FileWriter(f,true));
        CsvWriter csvWriter = new CsvWriter(writer, ',');
        // 读取表头
        r.readHeaders();//先读取表头
        String[] headers = r.getHeaders();//获取表头的数组
        //设置列头
        String[] csvHeaders = headers;
        //写入cvs文件
        csvWriter.writeRecord(csvHeaders);
        // 逐条读取记录，直至读完
        while (r.readRecord()) {
            // 读取一条记录
            String rawRecord = r.getRawRecord();
            String[] record = rawRecord.split(",");
          //  String[] wRecord = new String[5];

            csvWriter.writeRecord(record);
            System.out.println(rawRecord);
        }
        r.close();
        csvWriter.close();

    }
}
