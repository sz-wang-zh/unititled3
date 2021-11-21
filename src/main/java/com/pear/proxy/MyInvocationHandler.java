package com.pear.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    Object object;

    public MyInvocationHandler(Object obj) {
        this.object = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("how are you eat?");
        Object obj = method.invoke(object, args);
        return obj;

    }
}
