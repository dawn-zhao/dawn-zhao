package com.dawn.zhao.thread;

import java.util.concurrent.CountDownLatch;

public class DoEverythingThread extends Thread implements Runnable {

    private int sleepTime;

    private CountDownLatch latch;

    public DoEverythingThread(int sleepTime, CountDownLatch latch) {
        this.sleepTime = sleepTime;
        this.latch = latch;
        //设置此线程为守护线程,随着主线程死亡
        //setDaemon(true);
    }

    public DoEverythingThread() {
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
            System.out.println("线程"+sleepTime+Thread.currentThread().getName()+"要睡"+(sleepTime)+"ms");
            latch.countDown();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
