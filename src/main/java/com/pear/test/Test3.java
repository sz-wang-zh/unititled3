package com.pear.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 子类可以通过继承的方法来访问父类的private变量
 */
abstract class abstractTest {

}

public class Test3 extends PrivateClass{

    public static void test1(abstractTest abstractTest) {

    }
    public static void main(String[] args) {
//        Test3 test3 = new Test3();
//        test3.say();
//        System.out.println(Boolean.valueOf("1"));
//        if(Boolean.valueOf(""+"true ")) System.out.println("ture");
//        else System.out.println("false");
        Calendar instance = Calendar.getInstance();
        System.out.println(instance);
        System.out.println(instance.getWeekYear());

        System.out.println(instance.get(instance.YEAR));
        System.out.println(instance.get(instance.MONDAY));
        System.out.println(instance.get(instance.DATE));
        System.out.println(instance.get(instance.HOUR));
        System.out.println(instance.get(instance.MINUTE));
        System.out.println(instance.get(instance.AM));
        System.out.println(instance.get(instance.getFirstDayOfWeek()));
    }

}
