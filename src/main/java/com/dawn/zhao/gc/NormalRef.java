package com.dawn.zhao.gc;

import java.io.IOException;

/**
 * 普通的引用-强引用
 */
public class NormalRef {

    public static void main(String[] args) throws IOException {

        Domain domain = new Domain();

        domain = null;

        System.gc();

        System.in.read();
    }
}
