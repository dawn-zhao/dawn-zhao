package com.dawn.zhao.thread;

import com.alibaba.fastjson.JSONObject;

/**
 * 伪共享，只有多线程操作成员属性时会发送
 * http://ifeve.com/from-javaeye-false-sharing/
 */
public class FalseSharing extends Thread {

    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static
    {
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex)
    {
        this.arrayIndex = arrayIndex;
    }

    /**
     * 缓存行的状态
     * M(修改, Modified): 本地处理器已经修改缓存行, 即是脏行, 它的内容与内存中的内容不一样. 并且此cache只有本地一个拷贝(专有).
     * E(专有, Exclusive): 缓存行内容和内存中的一样, 而且其它处理器都没有这行数据
     * S(共享, Shared): 缓存行内容和内存中的一样, 有可能其它处理器也存在此缓存行的拷贝
     * I(无效, Invalid): 缓存行失效, 不能使用
     *
     *  6603474600ns 不注释    VolatileLong.p1p2p3p4p5p6
     * 33986072100ns 注释     VolatileLong.p1p2p3p4p5p6
     * 当p1p2p3p4p5p6存在时，一个VolatileLong对象的大小为64，一个long类型占8个字节，7个long类型+对象头固定占8字节一共占用64字节
     * 64个字节为普通缓存行的大小，即一个VolatileLong对象刚好填充一个缓存行，当多线程写入longs[n]时每个线程在操作不同的缓存行，
     * 因为多余的p1···等字段的填充，避免了伪共享，这个方法叫做补齐(Padding).
     *
     * 注释掉p1p2p3p4p5p6时，一个VolatileLong只有一个long类型属性，4个VolatileLong对象被填充至一个缓存行，
     * 当多线程写入long[n]时，同时操作一个缓存行，发生伪共享，cpu核心数越多执行效率差异越大
     *
     * 修改属性时，处理器会发出RFO(Request For Owner)请求，获取该行数据的权限，同时将该缓存行的状态变为I，此状态期间不允许别人修改，由此该处理器获取了该缓存行的绝对权限
     * 当多个处理器对该缓存行做写操作时，就会都发出RFO请求，造成效率问题，可以使用不同处理器操作不同的缓存行避免此类问题
     *
     */
    public static void main(final String[] args) throws Exception
    {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
        System.out.println(JSONObject.toJSONString(longs));
    }

    private static void runTest() throws InterruptedException
    {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            t.join();
        }
    }

    public void run()
    {
        long i = ITERATIONS + 1;
        while (0 != --i)
        {
            longs[arrayIndex].value = i;
        }
    }

    public final static class VolatileLong
    {
        public volatile long value = 0L;
        //定义属性做内存填充
        public long p1, p2, p3, p4, p5, p6; // comment out
    }
}
