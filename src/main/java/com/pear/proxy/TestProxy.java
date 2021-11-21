package com.pear.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/*
    jdk动态代理是实现类的接口，返回的对象类型也是类的接口类型
 */
public class TestProxy {
    public static void main(String[] args) {

        Man man = new Man();
        Class manClass = man.getClass();
        Field[] fields = manClass.getFields();

        Person person = (Person)Proxy.newProxyInstance(man.getClass().getClassLoader(),
                                    man.getClass().getInterfaces(),
                                    new MyInvocationHandler(man));
        person.say();
        person.toString();

        God god = (God)Proxy.newProxyInstance(man.getClass().getClassLoader(),
                man.getClass().getInterfaces(),
                new MyInvocationHandler(man));
        god.dodo();
    }
}
