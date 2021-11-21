package com.pear.Inherit;

import org.junit.Test;

class SuperClass {
    int i = 11;
    int s;
    SuperClass() {}

    public void say() {
        System.out.println("super say");
    }

    public void eat() {
        System.out.println("super eat");
        say();
    }

    public void say2() {
        System.out.println("super say："+i);
    }

    public void eat2() {
        System.out.println("super eat："+i);
    }
}

public class SubClass extends SuperClass{
    int i= 1;

    public static class a {
        int i = 1;
    }
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
    //        subClass.say();
    //        subClass.test1();

        subClass.test2();
    }

    /**
     * test01()的结果为：
     * super eat：20
     * sub eat：21没
     * 在子类没有隐藏所继承的变量时，通过super调用的变量和直接调用的是一样的
     */
    @Test
    public void test2() {
        i =21;


        super.i = 20;
        super.eat2();
        this.eat2();
    }

    /**
     * test1的结果为:
     * super eat
     * sub eat
     * 说明了super关键字调用的是父类的方法，但是如果该方法调用了其他方法，且子类重写了，那么调用的子类重写后的方法。
     */
    public void test1() {
        super.eat();
    }
    @Override
    public void say() {
        System.out.println("sub say");
    }

    @Override
    public void eat() {
        System.out.println("sub eat");
    }

    @Override
    public void say2() {
        System.out.println("sub say："+i);
    }

    @Override
    public void eat2() {
        System.out.println("sub eat："+i);
    }
}
