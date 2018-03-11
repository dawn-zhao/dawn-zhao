package com.dawn.zhao.thread;

import java.util.concurrent.*;

/**
 * 线程池的使用
 * 两种ExecutorService创建方式效果一样
 *
 * corePoolSize参数为线程池内运行线程的个数,同时运行线程个数
 * maximumPoolSize并非最大线程数量,可以理解为线程弹性扩张数
 * maximumPoolSize不能小于corePoolSize,否则会抛出异常IllegalArgumentException
 *
 */
public class ThreadPoolExecutorDemo {

    public static String lock = "just lock";


    public static void main(String[] args) {
//        testThreadPoolExector();
        ThreadPoolExecutorDemo executorDemo = new ThreadPoolExecutorDemo();
        executorDemo.multipleThreads();
    }

//  多线程并行后串行主线程
    private void multipleThreads() {
        AsyncThread asyncThread = new AsyncThread();
        new Thread(asyncThread).start();
    }

    class AsyncThread implements Runnable{
        @Override
        public void run() {
            BlockingQueue<Runnable> threads = new LinkedBlockingQueue<>();
            CountDownLatch latch = new CountDownLatch(50);
            DoEverythingThread doEverythingThread = null;
            for (int i=0;i<50;i++){
                doEverythingThread = new DoEverythingThread(100+i,latch);
                threads.add(doEverythingThread);
            }
            System.out.println("BlockingQueue done");

            long beginTime = System.currentTimeMillis();
            ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 50,500, TimeUnit.MILLISECONDS,threads);
            executorService.allowCoreThreadTimeOut(true);
            System.out.println(executorService.getTaskCount()+"个待执行的线程");

            executorService.prestartAllCoreThreads();
            try {
                latch.await(300, TimeUnit.SECONDS);
//            latch.await(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println("线程池中断,串行主线程错误!!!可能产生脏数据？");
                e.printStackTrace();
            }
            executorService.shutdownNow();
            System.out.println(executorService.toString());
            System.out.println(" 最大并行线程数 : "+executorService.getLargestPoolSize());
            System.out.println(" 执行完成的线程 : "+executorService.getCompletedTaskCount());

//        while (true){
//            if(executorService.getActiveCount() == 0){
//                executorService.shutdown();
//                break;
//            }
//            System.out.println("sleep");
//            Thread.sleep(1000);
//        }

//        while (true) {
//            if (executorService.getActiveCount() == 0) {
//                executorService.shutdown();
//                break;
//
//            }
//        }

//        while (executorService.awaitTermination(300, TimeUnit.SECONDS)){
//            System.out.println("waiting threads go done");
//        }
//        synchronized (lock){
//            lock.wait(300000);
//        }
            long endTime = System.currentTimeMillis();
            System.out.println();
            System.out.printf("睡醒了,线程池执行总时长%s毫秒!", endTime-beginTime);
            System.out.println();
        }
    }

    private static void testThreadPoolExector(){
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2,
                1L, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<>());

        executorService.execute(()->{
            int i=0;
            while (i<10000000){
                i++;
            }
            System.out.printf(" 我是第一个加入线程的,线程名字[%s]\n",Thread.currentThread().getName());
        });
        executorService.submit(()->{
            System.out.printf(" 我是第二个加入线程的,线程名字[%s]\n",Thread.currentThread().getName());
        });
        executorService.submit(()->{
            System.out.printf(" 我是第三个加入线程的,线程名字[%s]\n",Thread.currentThread().getName());
        });
        executorService.execute(()->{
            System.out.printf(" 我是第四个加入线程的,线程名字[%s]\n",Thread.currentThread().getName());
        });
        executorService.submit(()->{
            System.out.printf(" 我是第五个加入线程的,线程名字[%s]\n",Thread.currentThread().getName());
        });
        executorService.execute(()->{
            System.out.printf(" 我是第六个加入线程的,线程名字[%s]\n",Thread.currentThread().getName());
        });
        executorService.shutdown();
    }
}
