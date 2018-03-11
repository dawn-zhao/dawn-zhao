package com.dawn.zhao.lambda;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * {@link AutoCloseable}
 * 实现AutoCloseable或者Closeable接口的类。其资源获取可以在try()内,且最终会运行资源的close方法
 * 以下以redis连接为例
 */
public class AutoCloseableDemo {
    private static Properties properties = new Properties();

    private static GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    // 初始化Jedis连接池
    //private static JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 1000, "admin123");
    private static JedisPool jedisPool = new JedisPool(poolConfig, "192.168.99.100", 32768, 100000);

    /**
     * 普通操作执行
     *
     * @param executor
     * @return
     */
    public static Object execute(Function<Jedis, Object> executor) {

        try (   // 获取连接池里的连接  这里会自动释放资源
                Jedis jedis = jedisPool.getResource();
        ) {
            // 业务操作
            return executor.apply(jedis);
        }
    }


    /**
     * 普通操作执行 没有返回值NR not return
     *
     * @param executor
     */
    public static void executeNR(Consumer<Jedis> executor) {
        // 返回值
        try (   // 获取连接池里的连接  这里会自动释放资源
                Jedis jedis = jedisPool.getResource();
        ) {
            // 业务操作
            executor.accept(jedis);
        }
    }

    /**
     * 通过 pipeline 执行操作，并将所有的返回值以List的形式返回
     *
     * @param executor
     * @return
     */
    public static List<Object> executeWithPipline(Consumer<Pipeline> executor) {
        try (   // 获取连接池里的连接
                Jedis jedis = jedisPool.getResource();
                Pipeline pipeline = jedis.pipelined();
        ) {

            // 业务操作
            executor.accept(pipeline);
            return pipeline.syncAndReturnAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过 pipeline 执行操作，但是没有返回值 NR not return
     *
     * @param executor
     */
    public static void executeWithPiplineNR(Consumer<Pipeline> executor) {
        try (   // 获取连接池里的连接
                Jedis jedis = jedisPool.getResource();
                Pipeline pipeline = jedis.pipelined();
        ) {

            // 业务操作
            executor.accept(pipeline);
            pipeline.sync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
