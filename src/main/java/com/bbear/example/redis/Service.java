package com.bbear.example.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author junxiongchen
 * @date 2018/07/27
 */
public class Service {
    private static JedisPool jedisPool = null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(200);
        //最大空闲数
        jedisPoolConfig.setMaxIdle(8);
        //最大等待时间
        jedisPoolConfig.setMaxWaitMillis(100*1000);
        //borrow一个jedis实例是否需要验证，true则所有实例都可用
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.27.44", 9023, 3000);
    }

    DistributeLock distributeLock = new DistributeLock(jedisPool);
    int n = 500;

    public void demo() {
        String resource = distributeLock.lockWithTimeout("resource", 5000, 3000);
        System.out.println(Thread.currentThread().getName() + ",获得锁");
        System.out.println(--n);
        distributeLock.releaseLock("resource", resource);
    }
}
