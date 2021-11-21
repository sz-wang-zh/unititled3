package com.pear.net;

import org.junit.Test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestNet {
    @Test
    public void test01() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("10.41.154.2");
        try {
            InetAddress localHost = Inet4Address.getLocalHost();
//            localHost = Inet4Address.getByAddress("10.41.154.2".getBytes());
            System.out.println(localHost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("10.41.154.22");
        System.out.println(byName);
        System.out.println("10.41.154.2".getBytes().length);
    }
}
