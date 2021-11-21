package com.pear.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RedisTest {


    public Jedis getJedis() {
        Jedis jedis = new Jedis("192.168.241.136",6379);
        jedis.auth("123456");
        System.out.println("getJedis:  "+jedis.ping());
        return jedis;
    }

    /*
        原生使用java连接redis
     */
    @Test
    public void test01() {
        Jedis jedis = new Jedis("192.168.241.136",6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());
    }

    /*
        使用数据连接池操纵jredis
     */
    @Test
    public void test02() {
        JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(),"192.168.241.136",6379,100,"123456");
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());
    }


}
