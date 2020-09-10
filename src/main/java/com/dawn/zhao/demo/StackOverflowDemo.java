package com.dawn.zhao.demo;

public class StackOverflowDemo {

    public static void main(String[] args) {

        StackOverflowDemo demoClass4 = new StackOverflowDemo();

        demoClass4.a();
    }


    public void a(){
        a();
    }
}
