package com.pear.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test7 {

    // 系统的API不一定比自己写的快，短不代表快
    @Test
    public void test1() {
        Random random = new Random();
        int a = 0;
        int b = 0;
        int c = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            a = random.nextInt();
            b = random.nextInt();
            c = Math.max(a, b);

        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            a = random.nextInt();
            b = random.nextInt();
            if (a >= b) c = a;
            else c = b;

        }

        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test2() {
        System.out.println(0 + 1 - '0');
        System.out.println((char) 100);
        System.out.println((int) '0');
        System.out.println(-9 % 2);
    }

    //数字字符减0后本身的数字值
    @Test
    public void test3() {
        String a = "1111";
        String b = "1001";
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();
        System.out.println(ans.toString());
    }

    @Test
    public void test4() {
        int[] a = {1, 2, 3, 4, 5};
        ArrayList<Integer> list = new ArrayList<>();
        List<int[]> ints = Arrays.asList(a);
        System.out.println(ints.get(0)[1]);
    }

    @Test
    public void test5() {
        StringBuffer stringBuffer = new StringBuffer("adc");
        stringBuffer.append("d");
        System.out.println(stringBuffer.toString());
    }


    @Test
    public void test6() {

        Class<?> aClass;
        Driver driver = null;
        try {
            aClass = Class.forName("com.mysql.cj.jdbc.Driver");
            driver = (Driver) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/sc?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setDriver(driver);
        druidDataSource.setPassword("wangzheqq");
        try {
            DruidPooledConnection connection = druidDataSource.getConnection();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void test7() {
        byte b =90;
        System.out.println((char) b);

    }
}