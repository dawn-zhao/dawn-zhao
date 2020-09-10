package com.dawn.zhao;

import com.dawn.zhao.demo.DemoClass2;
import org.openjdk.jol.info.ClassLayout;

public class StaticAttr {

    public static String attr1 = "attr1";

    public static String getAttr1(){
        return attr1;
    }

    public String getAttr1_2(){
        return attr1;
    }

}
