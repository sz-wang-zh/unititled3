package com.pear;

import org.junit.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

public class Email {
    @Test
    public void test1() {
        System.out.println(isEmail("1@qq.1com"));

    }

    // 获取四位随机数
    @Test
    public void test2() {
        for (int i = 0; i < 100; i++) {
//            String s = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
//            System.out.println(s);
            System.out.println(Math.random());
        }

    }


    public  boolean isEmail(String email) {

        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

        Matcher matcher = emailPattern.matcher(email);

        if(matcher.find()){

            return true;

        }

        return false;

    }



}
