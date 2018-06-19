package com.dawn.zhao.demo._static;

import com.dawn.zhao.demo.DemoClass2;

import java.util.Objects;

public class StaticAttr {

    public static String name;

    static {
        if(Objects.isNull(name)){
            name = "Dawn Zhao";
        }
        System.out.println(" initialization staticAttr static ");
    }

    public static String getName() {
        return name;
    }

}
