package rpc.config.registry;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 通过redis模拟RPC注册中心
 */
public class RedisRegistryCenter {

    private static Jedis redisClient;

    /**
     * 工程初始化,建立jedis链接
     * @param host
     * @param port
     */
    public static void init(String host, int port) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);


        JedisPool jedisPool = new JedisPool(config, host, port);
        redisClient = jedisPool.getResource();
    }


    /**
     * 注册生产者
     */

    public static long registerProvider(String nozzle, String alias, String info) {
        return redisClient.sadd(nozzle + "_" + alias, info);
    }


    /**
     * 获取生产者
     */

    public static String obtainProvider(String nozzle, String alias) {
        return redisClient.srandmember(nozzle + "_" + alias);
    }

    public static Jedis getRedisClient() {
        return redisClient;
    }


}
