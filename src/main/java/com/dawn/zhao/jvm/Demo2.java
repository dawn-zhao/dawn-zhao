package com.dawn.zhao.jvm;

import org.openjdk.jol.info.ClassLayout;

public class Demo2 {

    public static class Lock{}

    static Lock lock = new Lock();

    public static void main(String[] args) {

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        new LockThread().start();

    }

    static class LockThread extends Thread {

        @Override
        public void run() {

            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        }
    }
}
