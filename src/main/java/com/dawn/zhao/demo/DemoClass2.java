package com.dawn.zhao.demo;

import com.dawn.zhao._interface.Action;
import com.dawn.zhao.bean.User;
import com.dawn.zhao.proxy.Play;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

public class DemoClass2 {
    static DemoClass2 demoClass2 = new DemoClass2();

    public static void main(String[] args) throws IOException {
//        String a = "sssssssss";
//
//        List<String> strings = new ArrayList<>();
//        strings.add("strings");
//        strings = addSome(strings);
//
//        Map<String, String> hashMap = new HashMap<>();
//        hashMap.put(null, "imnull");
//        System.out.println(hashMap.get(null));
//
//        Map linkedHashMap = new LinkedHashMap();
//
//        Map hashtable = new Hashtable();
//        hashtable.put("null", null);
//        List vector = new Vector();
//        vector.add("");


//        User user = new User();
//
//
//        InvocationHandler play = new Play(user);
//
//
//        Action dynamicProxy = (Action) Proxy.newProxyInstance(User.class.getClassLoader(),
//                User.class.getInterfaces(), play);
//
//        dynamicProxy.eat();

//        String aaaa  = new String("d");
//        String aaaa1 = aaaa.intern();
//        System.out.println(aaaa == aaaa1);
//        System.out.println(aaaa.equals(aaaa1));
//        String aaaa2  = "aaaaaaaaaaa";
//        System.out.println(aaaa2 == aaaa1);


//        char a;
//        outer: //this is the label for the outer loop
//        for(int i=0;i<10;i++) {
//            for(int j=0;j<10;j++) {
//                System.out.println("i = "+i+",j = "+j);
//                a=(char)System.in.read();
//                if(a=='b')
//                    break outer;
//                if(a=='c')
//                    continue outer;
//            }
//        }

        String a = "123";
        if("123"==a){
            System.out.println("你认为会被打印输出吗?");
        }



    }

    private static List addSome(List list){
        list.add(100);
        System.out.println(list.get(1));
        return list;
    }
}