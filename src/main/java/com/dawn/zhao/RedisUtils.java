package com.dawn.zhao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.Set;

public class RedisUtils {

    public static void main(String[] args) {

        String host = "192.168.1.41";
        String port = "6379";
        String password = "123456";

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(300);
        config.setMaxIdle(100);
        config.setMaxWaitMillis(2000);
        config.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(config, host, Integer.valueOf(port),
                Protocol.DEFAULT_TIMEOUT, password);
//        Set<String> keySet = jedis.keys("*");
//        for (String s : keySet) {
//            if(s.contains("user_lock_key_85")){
//                jedis.del(s);
//            }
//        }
//        jedisPool.returnResource(jedis);
        try(Jedis jedis = jedisPool.getResource()){
            String results = jedis.set("111key", "value", "NX", "PX", 10000);
            System.out.println(results);

            results = jedis.set("111key", "1111111111", "NX", "PX", 10000);
            System.out.println(results);

//            Long a = jedis.del("111key");
//            System.out.println(a);
        }
    }
}
