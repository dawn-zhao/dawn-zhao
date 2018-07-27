package com.dawn.zhao.sign;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class SignTest {

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void main(String[] args) throws Exception {
        String before = "abcdefgfedcba";
        System.out.println("加密前:"+before);

        byte[] plainText = before.getBytes("UTF-8");
        //产生一个RSA密钥生成器KeyPairGenerator(顾名思义：一对钥匙生成器)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //定义密钥长度1024位
        keyGen.initialize(1024);
        //通过KeyPairGenerator产生密钥,注意：这里的key是一对钥匙！！
        KeyPair key = keyGen.generateKeyPair();
        //获得一个RSA的Cipher类，使用公钥加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //System.out.println("/n" + cipher.getProvider().getInfo());
        System.out.println("用公钥加密...");
        //Cipher.ENCRYPT_MODE意思是加密，从一对钥匙中得到公钥 key.getPublic()
        System.out.println("Public Key : " + encryptBASE64(key.getPublic().getEncoded()));
        System.out.println(encryptBASE64(key.getPublic().getEncoded()));
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        //用公钥进行加密，返回一个字节流
        byte[] cipherText = cipher.doFinal(plainText);
        //以UTF8格式把字节流转化为String
        System.out.println(new String(cipherText));
        //使用私钥解密
        System.out.println("/n用私钥解密...");
        //Cipher.DECRYPT_MODE意思是解密模式，从一对钥匙中得到私钥 key.getPrivate()
        System.out.println("Private Key : " + encryptBASE64(key.getPrivate().getEncoded()));
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        //用私钥进行解密，返回一个字节流
        Long beginTime = System.currentTimeMillis();
        byte[] newPlainText = cipher.doFinal(cipherText);
        String after2 = new String(newPlainText, "UTF8");
        System.out.println("用私钥解密完成："+after2);
        System.out.println(System.currentTimeMillis()-beginTime);
    }

}
