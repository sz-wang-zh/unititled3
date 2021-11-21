package com.pear.test;

//import com.sun.org.apache.xpath.internal.axes.ChildTestIterator;
//import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.util.*;

/*
   子类实例化时父类的实例顺序
   1、子类的构造方法未调用父类的构造方法时，会自动在构造方法中添加默认构造方法：sup
   er()
   2、普通代码块会在类使用前执行，即在调用<clint>后和构造方法前。
   3、类未被加载时，在类加载机制的初始化阶段，会执行类构造器<clint>()方法，<clint>()方法由编译器自动收集类的所有类变量的赋值动作和静态语句块构成
   4、子类和父类都未被加载时，加载器会将子类和父类的类构造器<clint>()方法合为一个，且父类的<clint>()在子类前
   5、父类<clint> 子类<clint> 父类普通代码块 父类构造方法 子类普通代码块 子类构造

 */
class Parent
{
    //静态代码块，只在类在装载的时候执行
    static
    {
        System.out.println("parent static block");
    }

    //非静态代码块，每new一次都会执行
    {
        System.out.println("parent non-static block");
    }

    public  Parent()
    {
        System.out.println("parent constractor block");
    }

    public  Parent(int i)
    {
        System.out.println("parent parameter constractor block");
    }
}

class Child extends Parent
{
    //子类静态代码块
    static
    {
        System.out.println("child static block");
    }

    //子类非静态代码块
    {
        System.out.println("child non-static block");
    }

    //子类构造方法
    public Child()
    {
//        super(5);
        System.out.println("child constractor block");
    }

    //静态方法需要在调用的时候才执行
    public  static void get()
    {
        System.out.println("this is a static method!");
    }

}
public class Test6 {
    public static void  main(String[] args)
    {
//        new Parent();
        System.out.println("------------------");
        new Child();
        System.out.println("------------------");
        new Child();
        Child.get();
        Stack<String> stringStack = new Stack<>();
        stringStack.push("A");
        new Thread() {
            public void run() {
                System.out.println(stringStack.pop());
            }
        }.start();
        Date date = new Date();

    }
}
