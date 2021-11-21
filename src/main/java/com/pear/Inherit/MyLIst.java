package com.pear.Inherit;

import org.junit.Test;

import javax.rmi.CORBA.Stub;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyLIst<T> {
    public T t;
    public T test1(T a) {
        return a;
    }
    public <T> void test2(T a) {
        Throwable var2 = null;
    }

    public <T> String s(){
            T s;
        return null;
    }

    public <Y> String ss(){
        T s;
        Y y;
        return null;
    }


    public  <Y,S,H> T sss(Y aa){
        Y s = aa;
        Y y;
        System.out.println(s.getClass());
        return null;
    }


    @Test
    public void test01(){
        sss(new String("a"));
    }
}
