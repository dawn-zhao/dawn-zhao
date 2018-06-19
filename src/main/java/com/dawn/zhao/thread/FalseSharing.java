package com.dawn.zhao.thread;

import com.alibaba.fastjson.JSONObject;

/**
 * 伪共享，只有多线程操作成员属性时会
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
     *  5616237467ns 不注释VolatileLong.p1p2p3p4p5p6
     * 29207633375ns 注释VolatileLong.p1p2p3p4p5p6
     * 当p1p2p3p4p5p6存在时，一个VolatileLong对象的大小为64，一个long类型占8个字节，7个long类型+对象头固定占8字节一共占用64字节
     * 64个字节为普通缓存行的大小，即一个VolatileLong对象刚好填充一个缓存行，当多线程写入longs[n]时每个线程在操作不同的缓存行，
     * 因为多余的p1···等字段的填充，避免了伪共享，这个办法叫做补齐(Padding).
     *
     * 注释掉p1p2p3p4p5p6时，一个VolatileLong只有一个long类型属性，4个VolatileLong对象被填充至一个缓存行，
     * 当多线程写入long[n]时，同时操作一个缓存行，发生伪共享，cpu核心数越多执行效率差异越大
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
        public long p1, p2, p3, p4, p5, p6; // comment out
    }
}
