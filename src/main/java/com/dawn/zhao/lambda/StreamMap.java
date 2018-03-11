package com.dawn.zhao.lambda;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMap {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("sanchan", "coderknock", "Hello World");
        ////获取所有的单词                                            sanchan -> [sanchan ]       "Hello World" -> ["Hello","World"]
        System.out.println(JSON.toJSONString(strings.stream().parallel().map(str->{
            System.out.println(Thread.currentThread().getName());
            return str.split(" ");}).collect(Collectors.toList())));
        System.out.println(JSON.toJSONString(strings.parallelStream().map(str->{
            System.out.println(Thread.currentThread().getName());
            return str.split(" ");}).collect(Collectors.toList())));


        ////<R> Stream<R> map(Function<? super T, ? extends R> mapper);
        //System.out.println(JSON.toJSONString(strings.stream().map(str->str.split(" ")).map(Arrays::stream).collect(Collectors.toList())));
        ////<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
        //System.out.println(JSON.toJSONString(strings.stream().peek(System.out::println).map(str->str.split(" ")).peek(System.out::println).flatMap(Arrays::stream).collect(Collectors.toList())));
        //strings.stream().peek(System.out::println).anyMatch(str -> str.contains("c"));



        // map 对元素进行处理，映射为另一个元素
    }
}
