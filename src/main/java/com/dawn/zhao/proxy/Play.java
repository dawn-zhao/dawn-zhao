package com.dawn.zhao.proxy;

import com.dawn.zhao.bean.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Play implements InvocationHandler {

    User user;

    public Play() {
    }

    public Play(User user) {
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" before invoke ");
        method.invoke(user, args);
        System.out.println(" after invoke ");
        return null;
    }
}
