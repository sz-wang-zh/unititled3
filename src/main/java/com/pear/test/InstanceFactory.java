package com.pear.test;

class Instance {

    static Instance instance = new Instance();
    static {
        System.out.println("静态代码块1");
    }
    
    {
        System.out.println("普通代码块");
    }

    static {
        System.out.println("静态代码块2");
    }

    public Instance() {
        System.out.println("实例化");
    }
//    static Instance instance = new Instance(); // 不同的位置，不同的执行顺序

}
public class InstanceFactory {
    public static void main(String[] args) {

    }
}
