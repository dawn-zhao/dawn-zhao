package com.dawn.zhao.reordering;

public class ReorderExample {

    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1;          // 1
        flag = true;    // 2
    }

    public void reader() {
        if (flag) {            // 3
            int i = a * a; // 4
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int a = 0, b = 0;
        for (int i = 0; i < 100; i++) {
            Thread one = new Thread() {
                public void run() {
                }
            };
            Thread two = new Thread() {
                public void run() {
                }
            };
            one.start();
            two.start();
            one.join();
            two.join();
        }
    }
}
