package com.pear.thread;

class T4 extends Thread{
    volatile int i;
    @Override
    public void run() {
        System.out.println(i);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
    public void add() {
        i++;
    }
}
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        T4 t = new T4();
        t.start();

        Thread.sleep(10);
        t.add();
    }
}
