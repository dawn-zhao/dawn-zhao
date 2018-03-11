package com.dawn.zhao.DataEncryption;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 3DES
 * DESede
 * 加密算法,对称加密算法的一种
 * 大多数加密规则能支持加盐算法,盐值是解密的关键
 */
public class DES {

    private final static String KEY_DES = "DESede";
    static String tos(byte[] b) {
        String ans = "";
        for (int i = 0; i < b.length; i++) {
            ans += String.format("%02X", b[i]);
        }

        return ans;
    }
    public static void main(String[] args)
            throws NoSuchAlgorithmException,InvalidKeyException,
            NoSuchPaddingException, InvalidKeySpecException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] salt = "zhaoshuguang".getBytes();

        SecureRandom secure = new SecureRandom(salt);
        KeyGenerator generator = KeyGenerator.getInstance(KEY_DES);
        generator.init(secure);
        System.out.println(generator.generateKey().getAlgorithm());
        byte[] key = generator.generateKey().getEncoded();
//        DESKeySpec dks = new DESKeySpec(key);
//        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_DES);
//        SecretKey secretKey = factory.generateSecret(dks);
        // 上述三行代码与下面这行代码等效,但是上面代码只适用于DES,而下面的代码可以适用于很多其它加密方式
        // 实际上,下面这行代码会自动调用上述代码
        SecretKeySpec secretKey = new SecretKeySpec(key, KEY_DES);
        byte[] data = "zbcdefg".getBytes();
        Cipher cipher = Cipher.getInstance(KEY_DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypt = cipher.doFinal(data);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypt = cipher.doFinal(encrypt);

        System.out.println("密钥: " + tos(key));
        System.out.println("原始数据: " + tos(data));
        System.out.println("加密后: " + tos(encrypt));
        System.out.println("解密后:" + tos(decrypt));
    }
}
