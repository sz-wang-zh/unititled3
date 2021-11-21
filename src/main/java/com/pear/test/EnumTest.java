package com.pear.test;

import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.lang.reflect.Array;
import java.util.Arrays;

/*
    1、枚举类无法被实例化
    2、枚举类的成员的类型为本本类
    3、可以通过类.静态类变量.静态类变量.静态类变量。。。。
 */
enum SEASON{
            // enum类中的成员为本类对象
    spring,
    SUMMER("夏天","很热");
    String e;
    String e2;
    String e3;
    private SEASON() {

    }
    private SEASON(String e,String e2) {
        this.e = e;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        return "season{" +
                "e='" + e + '\'' +
                ", e2='" + e2 + '\'' +
                '}';
    }
}
public class EnumTest {
    @Test
    public void test01() {
//        System.out.println(SEASON.SUMMER.toString());
        try {
            Float aFloat = new Float(11);
            Double aDouble = new Double(11);
            System.out.println(aFloat.toString());
            System.out.println(aDouble.toString());
            int i =11;
            String a =i+"";
            System.out.println(a.length());
            System.out.println("12".length());
            Class<?> aClass = Class.forName("com.pear.test.SEASON");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SEASON summer = SEASON.SUMMER;

//        SEASON.SUMMER.name();
    }
}
