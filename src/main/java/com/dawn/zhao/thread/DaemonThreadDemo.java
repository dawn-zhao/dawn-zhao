package com.dawn.zhao.thread;

public class DaemonThreadDemo {

    public static void main(String[] args) {

        Thread mainThread = new Thread(()->{
            Thread childThread = new Thread(new DaemonThread());
            //setDaemon 需要在start前
            childThread.setDaemon(true);
            childThread.start();
            System.out.println("I'm main thread...");
        });
        mainThread.start();

    }
}

class DaemonThread implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.currentThread().sleep(1000);
                System.out.println("I'm child thread..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
