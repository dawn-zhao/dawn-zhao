package com.dawn.zhao.demo;

import org.aspectj.weaver.World;
import org.openjdk.jol.info.ClassLayout;

public class DemoClass3 {

    static class UserVo{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        UserVo o = new UserVo();
        o.setName("红");

//        System.out.println(ClassLayout.parseInstance(o).toPrintable());


        synchronized (o) {
            o.setName("明");
        }
    }
}
