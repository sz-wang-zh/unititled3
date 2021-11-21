package com.pear.test;

import javax.management.ObjectName;

class FooBar implements Runnable{
    Object obj1;
    Object obj2;

    public FooBar(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            synchronized (obj2) {

            }
        }
    }
}

public class Test5 {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        new Thread(new FooBar(obj1,obj2)).start();
        new Thread(new FooBar(obj2,obj1)).start();
    }
}
