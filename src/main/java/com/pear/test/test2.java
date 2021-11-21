package com.pear.test;

class tes<T> {
    T t;

    public  T t1(T t) {
        return t;
    }
    public String t2(T t) {
        return "A";
    }

    public  T t3(T t) {
        return t;
    }
}
public class test2 {
    InnerClass a = new InnerClass();
    int i = 10;
    public void say() {
        System.out.println("外部类 say");
    }
    public void eat() {
        i = 10;
        System.out.println("外部 eat");
    }

    class InnerClass {
        int i = 100;
        public void say() {
            System.out.println("内部类 say");
            System.out.println(this.i);
//            System.out.println(i);
//            System.out.println(test2.this.i);
        }

        public void eat() {
            say();
        }
    }

    public static void main(String[] args) {
        test2 test2 = new test2();
//        test2.say();
        test2.a.say();
//        test2.a.eat();
        // 内部类可以直接使用外部类的成员变量和方法，当内部类有同名的变量和方法时，会隐藏。
//        System.out.println(test2.i);
    }
}
