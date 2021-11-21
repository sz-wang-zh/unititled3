package com.pear.Inherit;

import com.pear.dc.ProtectSuperClass;
import org.junit.Test;

public class TestProtect  {

    class Father {
        int i = 1;
        public void changeI() {
            i = 9;
        }
    }
    class Son extends Father {
        public void invokeFatherI() {
            super.changeI();
        }
        public void superI() {
            System.out.println(super.i);
        }
    }

    @Test
    public void test01() {
        Son son = new Son();
        son.invokeFatherI();
        System.out.println(son.i);
        son.superI();
//        SubClass.a a = new SubClass.a();
//        SubClass subClass = new SubClass();
//        SubClass.a a = subClass.new a();
//         a.i=20;
//        System.out.println(a.i);
//
//        SubClass subClass1 = new SubClass();
//        SubClass.a aa = subClass1.new a();
//        System.out.println(aa.i);
         SubClass.a a = new SubClass.a();
    }
}
