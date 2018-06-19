package com.dawn.zhao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.Set;

public class RedisUtils {

    public static void main(String[] args) {

        String host = "192.168.0.110";
        String port = "6379";
        String password = "kokozu-redis-1029";

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(300);
        config.setMaxIdle(100);
        config.setMaxWaitMillis(2000);
        config.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(config, host, Integer.valueOf(port),
                Protocol.DEFAULT_TIMEOUT, password);
        Jedis jedis = jedisPool.getResource();
        Set<String> keySet = jedis.keys("*");
        for (String s : keySet) {
            if(s.contains("user_lock_key_85")){
                jedis.del(s);
            }
        }
        jedisPool.returnResource(jedis);
    }
}
