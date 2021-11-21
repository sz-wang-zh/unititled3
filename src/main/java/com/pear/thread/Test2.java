package com.pear.thread;

import java.util.concurrent.*;

class Thread2 extends Thread{
    static boolean flag = false;

    public Thread2(String s) {
        this.setName(s);
    }

    @Override
    public void run() {
        if (this.getName().equals("1")) {
            while (true){
//                System.out.println("hah");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(flag) System.out.println("duile ");

            }
        } else {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("修改了flag的值");
        }
    }
}

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        new Thread2("2").start();
        new Thread2("1").start();
        int i=0;
    }
}
