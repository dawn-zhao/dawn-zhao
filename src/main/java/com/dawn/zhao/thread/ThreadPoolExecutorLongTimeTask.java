package com.dawn.zhao.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorLongTimeTask {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        ExecutorService executorService=new ThreadPoolExecutor(1,20,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 1; i++) {
            executorService.submit(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()){

                }
            }));
        }

        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executorService.shutdownNow();
        }

        System.out.println(System.currentTimeMillis());
    }

}
