package com.pear.thread;

public class Test5 {

    public static void main(String[] args) {
      new Test().run1();
    }

}

class Test {
    int i =1;
    Integer I = 111;
    public void run1() {
        new Thread(){
            public void run(){
                i = 11;
                I = 111;
                System.out.println(i+" 5 "+I);
            }
        }.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i+" "+I);
    }
}