package com.dawn.zhao.lambda;

import com.alibaba.fastjson.JSON;
import com.dawn.zhao.thread.DoEverythingThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class LambdaThread {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>(50);
        while (strings.size()<50){
            strings.add(Math.random()+"");
        }

        BlockingQueue<Runnable> threads = new LinkedBlockingQueue<>();
        CountDownLatch latch = new CountDownLatch(50);
        int i = 0;
        for (;i<50;i++){
            threads.add(()->{
                System.out.println(JSON.toJSONString(strings));
                latch.countDown();
                strings.add(Math.random()+"");
            });
        }
        System.out.println("BlockingQueue done");

        long beginTime = System.currentTimeMillis();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 5,0, TimeUnit.NANOSECONDS,threads);
        System.out.println(executorService.getTaskCount()+"个待执行的线程");
        executorService.prestartAllCoreThreads();
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("线程池中断,串行主线程错误!!!可能产生脏数据？");
            e.printStackTrace();
        }
        executorService.shutdown();

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
        System.out.printf("线程池执行总时长%s毫秒!", endTime-beginTime);
        System.out.println();

    }

}
