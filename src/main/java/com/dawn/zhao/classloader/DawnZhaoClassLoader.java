package com.dawn.zhao.classloader;

public class DawnZhaoClassLoader extends ClassLoader {

    public static void main(String[] args) {
        DawnZhaoClassLoader dawnZhaoClassLoader = new DawnZhaoClassLoader();
        ClassLoader loader = dawnZhaoClassLoader.getClass().getClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }
}
