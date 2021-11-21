package com.pear.test;

public class Season {
    static Season s1;
    static Season s2;
    static Season s3;
    static String str = "2";

    public void t1() {

    }
    public static void main(String[] args) {
        Season season = new Season();
        System.out.println(season.str); // 2
        System.out.println(season.s1); // null
        System.out.println(s1.s2);     // null
        System.out.println(s1.s2.s1.s3);     // null

    }
}
