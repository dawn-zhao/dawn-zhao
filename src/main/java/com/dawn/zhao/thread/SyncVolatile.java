package com.dawn.zhao.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile修饰的属性做更新操作并不能保证原子性
 */
public class SyncVolatile {
    volatile static AtomicInteger atomicInteger = new AtomicInteger(0);
    static volatile Integer v1 = 0;
    static Integer v2 = 0;

    static int threadPooleSize = 10000;

    public static void main(String[] args) throws InterruptedException {
        volatileThreadTest();
        lockThreadTest();
        atomicThreadTest();

//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe.addressSize());
    }


    private static void atomicThreadTest() throws InterruptedException {
        long beginNano = System.nanoTime();
        BlockingQueue<Runnable> threads = new LinkedBlockingQueue<>(threadPooleSize);
        //定义latch
        CountDownLatch latch = new CountDownLatch(threadPooleSize);
        for (int i = 0; i < threadPooleSize; i++) {
            threads.add(new AtomicThread(latch));
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadPooleSize, threadPooleSize,300, TimeUnit.SECONDS,threads);
        threadPoolExecutor.prestartAllCoreThreads();
        //等待线程池所有线程执行完毕,等待5分钟没有执行完毕,终结线程池内的线程
        latch.await(300, TimeUnit.SECONDS);
        //关闭线程池内所有线程
        if(!threadPoolExecutor.isShutdown()){
            threadPoolExecutor.shutdown();
        }
        long endNano = System.nanoTime();

        System.out.println("atomic执行时间(纳秒):"+(endNano-beginNano)+",执行后值为:"+atomicInteger.get());
    }

    private static void volatileThreadTest() throws InterruptedException {

        long beginNano = System.nanoTime();
        BlockingQueue<Runnable> threads = new LinkedBlockingQueue<>(threadPooleSize);
        //定义latch
        CountDownLatch latch = new CountDownLatch(threadPooleSize);
        for (int i = 0; i < threadPooleSize; i++) {
            threads.add(new VolatileThread(latch));
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadPooleSize, threadPooleSize,300, TimeUnit.SECONDS,threads);
        threadPoolExecutor.prestartAllCoreThreads();
        //等待线程池所有线程执行完毕,等待5分钟没有执行完毕,终结线程池内的线程
        latch.await(300, TimeUnit.SECONDS);
        //关闭线程池内所有线程
        if(!threadPoolExecutor.isShutdown()){
            threadPoolExecutor.shutdown();
        }
        long endNano = System.nanoTime();

        System.out.println("volatile执行时间(纳秒):"+(endNano-beginNano)+",执行后值为:"+v1);
    }

    private static void lockThreadTest() throws InterruptedException {

        long beginNano = System.nanoTime();
        BlockingQueue<Runnable> threads = new LinkedBlockingQueue<>(threadPooleSize);
        //定义latch
        CountDownLatch latch = new CountDownLatch(threadPooleSize);
        for (int i = 0; i < threadPooleSize; i++) {
            threads.add(new LockThread(latch));
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadPooleSize, threadPooleSize,300, TimeUnit.SECONDS,threads);
        threadPoolExecutor.prestartAllCoreThreads();
        //等待线程池所有线程执行完毕,等待5分钟没有执行完毕,终结线程池内的线程
        latch.await(300, TimeUnit.SECONDS);
        //关闭线程池内所有线程
        if(!threadPoolExecutor.isShutdown()){
            threadPoolExecutor.shutdown();
        }
        long endNano = System.nanoTime();
        System.out.println("lock执行时间(纳秒):"+(endNano-beginNano)+",执行后值为:"+v2);
    }

    static class VolatileThread implements Runnable{

        public VolatileThread(CountDownLatch latch) {
            super();
            this.latch = latch;
        }

        //线程结束时标记,线程计数器
        private CountDownLatch latch;

        @Override
        public void run() {
            ++v1;
            latch.countDown();
        }
    }

    static class AtomicThread implements Runnable{
        public AtomicThread(CountDownLatch latch) {
            super();
            this.latch = latch;
        }

        //线程结束时标记,线程计数器
        private CountDownLatch latch;

        @Override
        public void run() {
            atomicInteger.incrementAndGet();
            latch.countDown();
        }
    }

    static class LockThread implements Runnable{

        public LockThread(CountDownLatch latch) {
            super();
            this.latch = latch;
        }

        //线程结束时标记,线程计数器
        private CountDownLatch latch;

        @Override
        public void run() {
            synchronized (SyncVolatile.class){
                ++v2;
                latch.countDown();
            }
        }
    }

}
