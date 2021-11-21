package com.pear.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
    数组加一
    注意点：可进位，
        特殊情况：当数组为9,9,9时，需要进一位，

 */
public class Easy3 {
    public static void add1(int num[]) {
        int i = num.length;
        while((++num[--i])==10&&i>0) {
            num[i] = 0;
        }
    }

        public static int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i]++;
                digits[i] = digits[i] % 10;
                if (digits[i] != 0) return digits;
            }
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
    public static void main(String[] args) {
        int a[] = {9,9};
//        add1(a);

//        a = new int[a.length+1];
//        a[0] = 11;
        System.out.println(Arrays.toString(plusOne(a)));
    }
}
