package com.dawn.zhao.gc;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class WeakRef {

    public static void main(String[] args) throws IOException {

        WeakReference<Domain> weakD = new WeakReference<>(new Domain());

        System.out.println(weakD.get());
        System.gc();
        System.out.println(weakD.get());

        ThreadLocal<Domain> threadLocal = new ThreadLocal<>();
        threadLocal.set(new Domain());

        threadLocal.remove();

    }
}
