package com.dawn.zhao.demo;

import com.alibaba.fastjson.JSONObject;
import com.dawn.zhao.bean.Account;
import com.dawn.zhao.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DemoClass2 {
    public static final int a = 1;

    public static void main(String[] args) throws InterruptedException, CloneNotSupportedException {

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setAddress("12312312");
        user.setUserName("111");
        user.setPhone("phone");
        users.add(user);
        User user2 = (User) user.clone();
        user2.setUserName("222");
        users.add(user2);
        System.out.println(JSONObject.toJSONString(users));
    }


}
