package com.pear.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *  Field通过反射拿到，是一个Field类型的变量，本身没有器对应的值。
 */
class A {
    public int a = 0;
    public void do1() {
        System.out.println("11");
    }
}

class B {
    public int a = 0;

}
public class TestField {
    public static void main(String[] args) throws Exception {
        A a1 = new A();
        a1.a = 11;
        Class<A> aClass = A.class;
        Field a = null;
        Field[] fields = aClass.getDeclaredFields();
        a = aClass.getDeclaredField("a");
        a.setAccessible(true);
        System.out.println(a.getClass());
        System.out.println(a.get(a1));
        String name = a.getName();
        a.set(a1,20);
        System.out.println(a1.a);



        Method aDo = aClass.getDeclaredMethod("do1");
        aDo.invoke(a1);
    }
}
