package com.pear.dc;

import org.junit.Test;

public class BigNumberM {
    public void start(String a,String b){
        //分割a和b
        //如果a或b的长度过小就不用分割
//        int n1 = a.length()<
    }
    @Test
    public void test01() {

    }

    @Test
    public void test02() {
        long stat = System.currentTimeMillis();
        long sum = 0l;
        for (long i = 0; i<=Integer.MAX_VALUE; i++)
            sum += i;
        long end = System.currentTimeMillis();
        System.out.println(end-stat);
//        System.out.println(sum);
    }
    
}
