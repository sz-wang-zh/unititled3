package com.pear.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//编译器要保证所有的情况下final变量会被赋值
public class FinalTest {
    class People {
        String name;
        String id;
        People(){}

        public People(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }



        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    @Test
    public void test1() {
        People people = new People("1", "2");
        People people2 = new People("-1", "2");
        final ArrayList arrayList = new ArrayList();

        arrayList.add(people);

        People people1 = (People) arrayList.get(0);
        people1.setId("0");

        arrayList.add(0,people2);
        System.out.println(people);
        System.out.println(arrayList.get(0));

//        arrayList.add(new People());
//        arrayList.add(new People("1","1"));
//        System.out.println(arrayList.get(1).toString());
//        arrayList.add(people);
    }

//
//    final static int j;
//
//    public FinalTest(int j) {
//        j = j;
//    }
//
//    public FinalTest() {
//    }
//    static {
//        j =9;
//}
//
//    public static void main(String[] args) {
//
////        FinalTest finalTest = new FinalTest(1);
////        System.out.println(FinalTest.j);
//        long startTime = 0;
//        long endTime = 0;
//        long sum = 0;
//        for (int i = 0; i < 1000; i++) {
//            startTime= System.currentTimeMillis();
//            try {
//                Thread.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            endTime = System.currentTimeMillis();
//
//            sum=sum+endTime-startTime;
//        }
//
//        System.out.println(sum);
//    }
}
