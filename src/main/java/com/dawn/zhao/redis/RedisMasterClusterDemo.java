package com.dawn.zhao.redis;

import com.dawn.zhao.dateutils.DateUtil;
import com.google.common.collect.Sets;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * 多主多从集群
 */
public class RedisMasterClusterDemo {

    static Set<HostAndPort> nodes = Sets.newHashSet(
            HostAndPort.parseString("192.168.100.7:6380"),
            HostAndPort.parseString("192.168.100.8:6380"),
            HostAndPort.parseString("192.168.100.9:6380"),
            HostAndPort.parseString("192.168.100.7:6381"),
            HostAndPort.parseString("192.168.100.8:6381"),
            HostAndPort.parseString("192.168.100.9:6381")
    );

    public static void main(String[] args) throws IOException {

//        JedisCluster cluster = new JedisCluster(nodes);
//
//        cluster.set("a:b:c:d", "1351621321321");
//
//        String value = cluster.get("a:b:c:d");
//
//        System.out.println(value);
//
//        cluster.close();

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1587031800099L)));

    }
}
