package com.dawn.zhao.gc;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 软引用
 * Vm Options -Xmx20
 */
public class SoftRef {

    public static void main(String[] args) throws IOException {

        SoftReference<byte[]> sd = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(sd.get());

        System.gc();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sd.get());

        byte[] b = new byte[1024*1024*12];
        System.out.println(sd.get());


    }
}
