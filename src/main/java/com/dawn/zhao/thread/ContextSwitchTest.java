package com.dawn.zhao.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * 测试
 */
public class ContextSwitchTest {

    static final int RUNS = 3;

    static final int ITERATES = 10000;

    static AtomicReference turn = new AtomicReference();

    static final class WorkerThread extends Thread {

        public WorkerThread(String name) {
            super(name);
        }

        public WorkerThread() {
        }

        volatile Thread other;
        volatile int nparks;

        public void run() {
            final AtomicReference t = turn;
            final Thread other = this.other;
            if (turn == null || other == null)
                throw new NullPointerException();
            int p = 0;
            for (int i = 0; i < ITERATES; ++i) {
                while (!t.compareAndSet(other, this)) {
                    LockSupport.park();
                    ++p;
                }
                LockSupport.unpark(other);
            }
            LockSupport.unpark(other);
            nparks = p;
            System.out.println("parks: " + p);
        }
    }


    static void test() throws Exception {

        WorkerThread a = new WorkerThread();
        WorkerThread b = new WorkerThread();

        a.other = b;
        b.other = a;
        turn.set(a);
        long startTime = System.nanoTime();
        a.start();

        b.start();

        a.join();

        b.join();

        long endTime = System.nanoTime();

        int parkNum = a.nparks + b.nparks;
        System.out.println("Average time: " + ((endTime - startTime) / parkNum)  + "ns");

    }


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < RUNS; i++) {
            test();
        }
    }

//    public static void main(String[] args) throws Exception
//    {
//        Thread thread = Thread.currentThread();
//
//        LockSupport.unpark(thread);
//
//        System.out.println("a");
//        LockSupport.park();
//        System.out.println("b");
//        LockSupport.park();
//        System.out.println("c");
//    }

    public static void t2() throws Exception
    {
        Thread t = new Thread(new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000)
                {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();


        System.out.println("main over");
    }

//    public static void main(String[] args) throws Exception {
//        t2();
//    }
}