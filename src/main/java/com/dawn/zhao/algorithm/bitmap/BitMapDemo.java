package com.dawn.zhao.algorithm.bitmap;

import java.util.Random;

/**
 * https://blog.csdn.net/a3192048/article/details/80261699
 */
public class BitMapDemo {

    private static final int CAPACITY = 1000000000;//数据容量

    // 定义一个byte数组缓存所有的数据
    // 1 << 29 536870912
    private byte[] dataBytes = new byte[1 << 29];

    public static void main(String[] args) {

        BitMapDemo bm = new BitMapDemo();

        byte[] bytes = null;

        Random random = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            int num = random.nextInt();
            bytes = bm.splitBigData(num);
            if(i % 10000000 == 0) {
                System.out.println("读取了第 " + (i + 1) + "\t个数: " + num);
            }
        }
        bm.lastNum(bytes);
        bm.output(bytes);
    }


    /**
     * 读取数据，并将对应数数据的 到对应的bit中，并返回byte数组
     * @param num 读取的数据
     * @return byte数组  dataBytes
     */
    private byte[] splitBigData(int num) {

        //获取num数据对应bit数组（虚拟）的索引
        long bitIndex = num + (1L << 31);

        //bit数组（虚拟）在byte数组中的索引
        int index = (int) (bitIndex / 8);

        //bitIndex 在byte[]数组索引index 中的具体位置
        int innerIndex = (int) (bitIndex % 8);

        dataBytes[index] = (byte) (dataBytes[index] | (1 << innerIndex));

        return dataBytes;
    }

    /**
     * 获取最后一个非0数,最后一个数组为最大值
     * @param bytes byte数组
     */
    private void lastNum(byte[] bytes){
        for (int i = bytes.length -1; i>0 ;i--) {
            for (int j = 7; j>=0; j--) {
                if (!(((bytes[i]) & (1 << j)) == 0)) {
                    int number = (int) ((((long) i * 8 + j) - (1L << 31)));
                    System.out.println("最大值 : "+number);
                    return;
                }
            }
        }
    }

    /**
     * 输出数组中的数据
     * @param bytes byte数组
     */
    private void output(byte[] bytes) {
        int count = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(((bytes[i]) & (1 << j)) == 0)) {
                    count++;
                    int number = (int) ((((long) i * 8 + j) - (1L << 31)));
                    System.out.println("OUTPUT num : " + number + "; index : " + i + "; innerIndex : " + j);
                }
            }
        }
    }

}
