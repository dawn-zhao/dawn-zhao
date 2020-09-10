package com.dawn.zhao.thread;

/**
 * Volatile共享变量例子
 */
public class VolatileExample2 extends Thread {

    static volatile int i = 1;

    public static void main(String[] args) throws Exception {
        i++;
        System.out.println(i);
    }
}