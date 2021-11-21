package com.pear.algorithm;

import java.util.*;

public class Huiwen {

    public static boolean isPalindrome(int x) throws NullPointerException{
        if(x<10&&x>=0) return true;
        if(x<0||x%10==0) return false;
        // x1为x的前length/2的数字串，x2为x的后length/2的数字串

        int x1,x2=0;
        int length = String.valueOf(x).length();
//        System.out.println(length%2);
        if (length%2>0) x1 = x/(int)Math.pow(10,length/2+1);
            else x1 = x/(int)Math.pow(10,length/2);
//        System.out.println(x1);

        for (int i = 0; i<length/2; i++) {
            x2 = x2 * 10 + x%10;
            x/=10;
        }
//        System.out.println(x1 + " "+ x2);
        return x1==x2;
    }

    public static void main(String[] args) {
        int n = -121;
//        int length = String.valueOf(n).length();
        System.out.println(isPalindrome(n));
//        System.out.println(n+" "+Math.pow(10,length/2-1));
//        System.out.println(n/(int)Math.pow(10,length/2));
//        System.out.println(100/10>0);
//        System.out.println(5 / (int)(Math.pow(10,0)));
//        System.out.println(Math.pow(10,4));

    }

}
