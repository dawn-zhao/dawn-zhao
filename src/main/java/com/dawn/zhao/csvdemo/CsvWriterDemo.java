package com.dawn.zhao.csvdemo;

import com.aliyun.oss.OSSClient;
import com.csvreader.CsvWriter;
import com.dawn.zhao.demo.FieldAnnotationDemo;
import com.dawn.zhao.ossdemo.OssStreamDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class CsvWriterDemo {

    public static void main(String[] args) {
//        writer();
        writer2();
    }

    public static void writer(){
        String filePath = "C:/Users/76456/Desktop/test.csv";

        try {

            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("UTF-8"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头
            String[] headers = FieldAnnotationDemo.getFieldsName();
            String[] content = {"12365","张山","34"};
            csvWriter.writeRecord(headers);
            csvWriter.writeRecord(content);
            csvWriter.writeRecord(content);
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writer2(){
        String filePath = "C:/Users/76456/Desktop/test.csv";

        try (
            ByteArrayOutputStream stream = new ByteArrayOutputStream(100000);
        )
        {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(stream,',', Charset.forName("UTF-8"));

            // 写表头
            String[] headers = FieldAnnotationDemo.getFieldsName();
            String[] content = {"12365","张山","34"};
            csvWriter.writeRecord(headers);
            //先close再对流进行操作,否则流为空字符
            csvWriter.close();
            InputStream stream1 = new ByteArrayInputStream(stream.toByteArray());
            OSSClient ossClient = OssStreamDemo.getOssClient();
            ossClient.putObject("cias", "cias/cias-report/develop/demo.csv", stream1, OssStreamDemo.getMetadata("demo.csv",stream1));
            ossClient.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
