package com.dawn.zhao.lambda;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 普通匿名Thread类的实现以及lambda实现Thread匿名类
 *
 * 普通排序以及lambda实现排序
 *
 */
public class AnonymousClassDemo {

    public static void main(String[] args) throws InterruptedException {
        //匿名类的实现方式
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 5; i++) {
                            System.out.print(i + " ");
                        }
                    }
                }
        );
        t.start();
        //Thread.join 等待此线程执行完毕
        t.join();
        System.err.printf("\n%s\n","------------------");

//      Lambda的实现方式
        t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.print(i + " ");
            }
        });
        t.start();

//      排序实现
//      普通实现
        List<Integer> intList = Arrays.asList(12, 232, 432, 4352, 654643, 6572, 3242, 1, 23, 35, 4654);
        intList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                System.out.println(o1 +"-"+ o2+"  大于0? : "+(o1 - o2>0));
                return o1 - o2;
            }
        });
//      lambda实现排序
        intList.sort((a, b) -> a - b);
        System.out.println("\n\n\n"+ JSON.toJSONString(intList));
    }
}
