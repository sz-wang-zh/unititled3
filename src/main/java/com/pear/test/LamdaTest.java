package com.pear.test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface Person {
    void say();
}

interface book {
    void number();
}
public class LamdaTest {


    public static void main(String[] args) {
        Person person = ()-> System.out.println("akjfa"
        );
        person.say();

        String s1 = "abc";
        String s2 = s1;
        System.out.println(s1==s2);

        String s3 = new String("abc");
        String s4 = s3.intern();
        System.out.println(s1==s3);
        System.out.println(s1==s4);
        System.out.println(s3==s4);
    }

}
