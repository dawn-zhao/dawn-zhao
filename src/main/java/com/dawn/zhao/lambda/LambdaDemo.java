package com.dawn.zhao.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class LambdaDemo {

    public static void main(String[] args) {

        //如果你的参数列表是唯一的，即两个参数的方法只有一个，省略参数类型
        Comparator<Integer> comparator = (int1, int2) -> {
            return int1 - int2;
        };
        //参数列表只有一个参数，那么可以省略括号
        Comparable<Integer> comparable = (Integer intVal) -> {
            return 1;
        };
        //如果业务语句只有一条可以省略 {}
        Comparable<Integer> comparable2 = intVal -> 1;


        List<String> words = Arrays.asList("abcdefg", "plmojb", "uhb ijn");


        System.out.println(words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .peek(System.out::println)
                .distinct()
                .collect(Collectors.toList()));
        System.out.println("==============================");
        System.out.println(words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .peek(System.out::println)
                .distinct()
                .collect(Collectors.toList()));

        List<Integer> intList = Arrays.asList(12, 232, 432, 4352, 654643, 6572, 3242, 1, 23, 35, 4654);
        System.out.println(intList.stream().collect(Collectors.groupingBy(x -> x % 2)));
    }
}
