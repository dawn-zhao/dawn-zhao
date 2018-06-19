package com.dawn.zhao.exception;

import sun.awt.SunToolkit;

/**
 * 异常优化
 * 1、去掉异常栈构造，重写fillInStackTrace，定义空实现
 * 2、业务去异常，优化利用异常流来处理业务的程序
 * <link>http://blog.hesey.net/2013/11/eliminate-exception-overhead-in-java.html#more-1358</link>
 */
public class ExceptionOptimization {

    static Integer num = 10000;

    public static void main(String[] args) {
        dawnExceptionTest();
        zhaoExceptionTest();
        SunToolkit.OperationTimedOut operationTimedOut = new SunToolkit.OperationTimedOut();

    }

    private static void dawnExceptionTest(){
        long begin = System.nanoTime();
        for (int i = 0; i < num; i++) {
            DawnException dawnException = new DawnException();
        }
        long end = System.nanoTime();
        System.out.println("dawnExceptionTest耗时:"+(end-begin));
    }

    private static void zhaoExceptionTest(){
        long begin = System.nanoTime();
        for (int i = 0; i < num; i++) {
            ZhaoException zhaoException = new ZhaoException();
        }
        long end = System.nanoTime();
        System.out.println("zhaoExceptionTest:"+(end-begin));
    }

    static class DawnException extends Exception{
        @Override
        public synchronized Throwable fillInStackTrace() {
            return null;
        }
    }

    static class ZhaoException extends Exception{
        @Override
        public synchronized Throwable fillInStackTrace() {
            return super.fillInStackTrace();
        }
    }
}
