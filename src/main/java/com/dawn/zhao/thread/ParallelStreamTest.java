package com.dawn.zhao.thread;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamTest {

    public static void main(String[] args) {
        List<Long> longs = new ArrayList<>();
        for (long i = 0; i < 1000000; i++) {
            longs.add(i);
        }
        longs.parallelStream().forEach(l->{
            System.out.println(Thread.currentThread().getName());
        });
    }
}
