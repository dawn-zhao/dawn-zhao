package com.dawn.zhao.demo;

public class OutOfMemoryDemo {


//    byte[] byte_array = new byte[1024*1024*11];

    //Vm Options : -Xmx 10M
    public static void main(String[] args) {

        byte[] byte_array = new byte[1024*1024*11];
        System.out.println(byte_array.length);
    }

}
