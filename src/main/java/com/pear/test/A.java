package com.pear.test;

class B{

}

class C {
    final static int i = 1;
    static {
        System.out.println("haha");
    }
    {
        System.out.println("hehe");
    }
    static {
        System.out.println("cici");
    }
}
public class A extends B{
    static {
        System.out.println("aa");
    }
    public static void main(String[] args) {
        A a = new A();
        B a1 = new A();
        System.out.println(a.getClass());

        System.out.println(a1.getClass());
        System.out.println(C.i);
        new C();
    }

}
