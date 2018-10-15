package com.dawn.zhao.thread;

import org.apache.tomcat.jni.Thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.execute(new BlockThread());
            System.out.println("线程数 : id : "+i);
        }
    }

    static class BlockThread implements Runnable {
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
