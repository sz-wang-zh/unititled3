package com.pear.test;

/*
    静态变量赋值和静态代码块相关时，具体的值是按顺序决定的，使用的值是后面代码的赋值结果。
    静态代码块的执行在”初始化“的阶段执行
    当变量被final修饰时,变量只能被赋值一次，变量的赋值与顺序无关（只能赋值一次，否则出错），当是在定义时赋值时，会在“准备”阶段赋值
    静态变量在被准备阶段开始赋值，而非静态代码块在构造方法后执行，

    被final修饰的变量：三种赋值方式
        1、在定义时直接赋值。
        2、声明时不赋值，在constructor中赋值（最常用的方式）
        3、声明时不赋值，在构造代码块中赋值

    被final static修饰的变量：两种赋值方式
       1、在定义时直接赋值.
       2、在静态代码块里赋值
 */
class s {

    static {
        string = "2";
    }

    static String string = "1";

    static {
        string = "3";
        System.out.println("d");
    }


    static {
        System.out.println("a");
        string2= "2";
        System.out.println("b");
    }

    final  static String string2;

    static {
        System.out.println("c");
//        string2 = "3";
        System.out.println("d");
    }


    public static void setString(String string) {
        s.string3 = string;
    }

    static String string3;

}
public class Test4 {
    public static void main(String[] args) {
//        try {
//            Class.forName("com.pear.test.A" +
//                    "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(s.string);
        System.out.println(s.string2);
    }
}
