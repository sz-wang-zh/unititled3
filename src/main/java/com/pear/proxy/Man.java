package com.pear.proxy;

public class Man implements Person,God {

    @Override
    public void say() {
        System.out.println("man say...");
    }

    @Override
    public void eat() {
        System.out.println("man eat...");
    }
    public void look() {
        System.out.println("look");
    }

    @Override
    public void dodo() {
        System.out.println("dodo");
    }
}
