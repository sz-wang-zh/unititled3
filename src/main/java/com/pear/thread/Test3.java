package com.pear.thread;


public class Test3 {
    public static void main(String[] args) {
        Aobing a = new Aobing();
        a.start();
        for (; ; ) {
            if(a.isFlag()){
                System.out.println("有点东西");
            }
        }
    }
}

class Aobing extends Thread {
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + flag);

    }
}
