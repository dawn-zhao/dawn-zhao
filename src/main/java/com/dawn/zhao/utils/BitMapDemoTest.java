package com.dawn.zhao.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Random;

public class BitMapDemoTest {

    private static final int CAPACITY = 200;//数据容量

    // 定义一个byte数组缓存所有的数据
    private byte[] dataBytes = new byte[1 << 5];

    public static void main(String[] args) {

        BitMapDemoTest bm = new BitMapDemoTest();

        byte[] bytes = null;

        Random random = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            int num = random.nextInt(192);
            bytes = bm.splitBigData(num);
        }

        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]+", ");
        }

        bm.output(bytes);

    }

//    public static void main(String[] args) {
//        for (int j = 0; j < 8; j++) {
//            int number = (int) ((((long) 32 * 8 + j) - (1L << 6)));
//            System.out.println(number);
//        }
//    }

    /**
     * 读取数据，并将对应数数据的 到对应的bit中，并返回byte数组
     * @param num 读取的数据
     * @return byte数组  dataBytes
     */
    private byte[] splitBigData(int num) {

        //获取num数据对应bit数组（虚拟）的索引
        long bitIndex = num + (1L << 6);

        //bit数组（虚拟）在byte数组中的索引
        int index = (int) (bitIndex / 8);

        //bitIndex 在byte[]数组索引index 中的具体位置
        int innerIndex = (int) (bitIndex % 8);

        System.out.println("INPUT num : " + num + "; index : " + index + "; innerIndex : " + innerIndex);

        dataBytes[index] = (byte) (dataBytes[index] | (1 << innerIndex));
        return dataBytes;
    }

    /**
     * 输出数组中的数据
     * @param bytes byte数组
     */
    private void output(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(((bytes[i]) & (1 << j)) == 0)) {
                    int number = (int) ((((long) i * 8 + j) - (1L << 6)));
                    System.out.println("OUTPUT num : " + number + "; index : " + i + "; innerIndex : " + j);
                }
            }
        }
    }

}
