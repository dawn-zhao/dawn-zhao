package com.dawn.zhao.ossdemo;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.DefaultCredentials;
import com.aliyun.oss.model.GenericRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OssStreamDemo {

//    # config aliyun oss
//    aliyun.url=http://cias.oss.komovie.cn/
//    aliyun.internal.url=http://cias.oss.komovie.cn/
//
//    aliyun.accessid=EGXkCLXwYIryb7xp
//    aliyun.accesskey=9tqYqzbYq3jRtmZ8nhE1mADEGdqr8R
//    aliyun.bucketName=cias
//# 配置开发、测试、正式存储路径
//    aliyun.path=cias-advert/device_test/

    public static void main(String[] args) {
        ClientConfiguration conf = new ClientConfiguration().setSupportCname(false);
        com.aliyun.oss.common.auth.Credentials credentials = new DefaultCredentials("EGXkCLXwYIryb7xp", "9tqYqzbYq3jRtmZ8nhE1mADEGdqr8R");
        OSSClient client = new OSSClient("http://oss.komovie.cn/", new DefaultCredentialProvider(credentials), conf);
//        http://cias.oss-cn-hangzhou.aliyuncs.com/cias/report/110/%E8%AE%A2%E5%8D%95%E6%8A%A5%E8%A1%A8%E6%95%B0%E6%8D%AE2018%E5%B9%B401%E6%9C%88.csv
        try{
            OSSObject ossObject = client.getObject("cias","cias/report/110/订单报表数据2018年01月.csv");
            System.out.println(ossObject.toString());
        } catch(OSSException e){
            System.out.println("文件不存在啊,坟蛋!!");
        }
    }

    public static void streamDemo() throws IOException {

        ClientConfiguration conf = new ClientConfiguration().setSupportCname(false);
        com.aliyun.oss.common.auth.Credentials credentials = new DefaultCredentials("EGXkCLXwYIryb7xp", "9tqYqzbYq3jRtmZ8nhE1mADEGdqr8R");
        OSSClient client = new OSSClient("http://oss.komovie.cn/", new DefaultCredentialProvider(credentials), conf);

        InputStream instream = genFixedLengthInputStream(128 * 1024);

        ObjectMetadata objectMeta = new ObjectMetadata();
        client.putObject("cias", "cias/cias-report/demo.txt", instream,objectMeta);
        client.shutdown();

    }

    public static OSSClient getOssClient(){

        ClientConfiguration conf = new ClientConfiguration().setSupportCname(false);
        com.aliyun.oss.common.auth.Credentials credentials = new DefaultCredentials("EGXkCLXwYIryb7xp", "9tqYqzbYq3jRtmZ8nhE1mADEGdqr8R");
        OSSClient ossClient = new OSSClient("http://oss.komovie.cn/", new DefaultCredentialProvider(credentials), conf);
        return ossClient;

    }

    public static ObjectMetadata getMetadata(String fileName, InputStream instream) throws IOException {

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(instream.available());
        objectMeta.setCacheControl("no-cache");
        objectMeta.setHeader("Pragma", "no-cache");
        objectMeta.setContentDisposition("inline;filename=" +fileName);
        return objectMeta;
    }

    public static InputStream genFixedLengthInputStream(long fixedLength) {
        byte[] buf = new byte[(int)fixedLength];
        for (int i = 0; i < buf.length; i++)
            buf[i] = 'a';
        return new ByteArrayInputStream(buf);
    }
}
