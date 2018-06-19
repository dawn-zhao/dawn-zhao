package com.dawn.zhao.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShutdownHook {

    private static final void shutdownCallback() {
        System.out.println("Shutdown callback is invoked.");
    }

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdownCallback();
            }
        });
        for (int i = 0; i < 100; i++) {
            System.out.println("程序执行 "+i);
            Thread.sleep(1000);
        }

    }
}
