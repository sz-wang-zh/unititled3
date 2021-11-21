package com.pear.thread;

class Thread1 extends Thread {

    static volatile int i = 1;

    public Thread1(String s) {
        this.setName(s);
    }

    @Override
    public void run() {
        System.out.println(this.getName());
        if(this.getName().equals("1")) {
            while (true) {
                System.out.println(this.getName()+" "+i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            i=0;
        }
    }
}
class Thread11 extends Thread {
    @Override
    public void run() {
        System.out.println(this.getState());
    }
}

public class Test1 {
    public static void main(String[] args) {
//        new Thread1("1").start();
//        new Thread1("2").start();
//        System.out.println(Thread1.i);
        Thread11 thread11 = new Thread11();
        System.out.println(thread11.getState());
        thread11.start();
        thread11.start();
//        thread11.start();
        System.out.println(thread11.getState());
    }
}
