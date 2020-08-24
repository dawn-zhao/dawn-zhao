package com.dawn.zhao.reflect;

/**
 * 反射
 * 通过class对象，实现对对象实例的操作
 * 获取Class对象的三种方式
 *  1、Class.forName("全路径类名称"); 例如 Class.forName("com.dawn.zhao.reflect.World");
 *  2、类名.class
 *  3、对象.getClass
 */
class World {

    private String people;

    private String job;

}

public class Reflect {

    public static void main(String[] args) throws ClassNotFoundException {

        Class class1 = Class.forName("com.dawn.zhao.reflect.World");
        System.out.println(class1.getName());

        Class class2 = World.class;
        System.out.println(class2.getName());

        Class class3 = new World().getClass();
        System.out.println(class3.getName());
    }
}