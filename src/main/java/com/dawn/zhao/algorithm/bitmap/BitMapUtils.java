package com.dawn.zhao.algorithm.bitmap;

/**
 * https://blog.csdn.net/a3192048/article/details/80261699
 */
public class BitMapUtils {

    private byte[] dataBytes;

    public BitMapUtils(int capacity){
        this.dataBytes = new byte[capacity];
    }

    public boolean put(Integer num) {
        splitBigData(num);
        return true;
    }

    /**
     * 读取数据，并将对应数数据的 到对应的bit中，并返回byte数组
     * @param num 读取的数据
     * @return byte数组  dataBytes
     */
    private void splitBigData(int num) {

        //获取num数据对应bit数组（虚拟）的索引
        long bitIndex = num + (1L << 31);

        //bit数组（虚拟）在byte数组中的索引
        int index = (int) (bitIndex / 8);

        //bitIndex 在byte[]数组索引index 中的具体位置
        int innerIndex = (int) (bitIndex % 8);

        dataBytes[index] = (byte) (dataBytes[index] | (1 << innerIndex));

    }

    /**
     * 获取最后一个非0数,最后一个数组为最大值
     */
    public int lastNum(){
        for (int i = dataBytes.length -1; i>0 ;i--) {
            for (int j = 7; j>=0; j--) {
                if (!(((dataBytes[i]) & (1 << j)) == 0)) {
                    int number = (int) ((((long) i * 8 + j) - (1L << 31)));
                    return number;
                }
            }
        }
        return 0;
    }
}
