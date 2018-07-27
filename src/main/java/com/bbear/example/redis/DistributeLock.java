package com.bbear.example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.UUID;

/**
 * redis 分布式锁 ：
 * setNx作为锁的关键，indentifier作为标示，判断该锁是否加锁和释放
 * @author junxiongchen
 * @date 2018/07/27
 */
public class DistributeLock {
    private final JedisPool jedisPool;

    public DistributeLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 加锁
     * @param lockName 锁的标示
     * @param acquireTime 获取锁的时间
     * @param timeout 设置超时时间
     * @return 锁标示名称
     */
    public String lockWithTimeout(String lockName,long acquireTime,long timeout){
        Jedis jedis = null;
        try {
            //获取链接
            jedis = jedisPool.getResource();
            //随机生成一个value标示
            String indentifier = UUID.randomUUID().toString();
            //锁名，即key值
            String lockKey = "lock:" + lockName;
            System.out.println(lockKey);
            //超时时间，上锁后如果超过该时间则自动释放锁
            int lockExpire = (int) (timeout / 1000);
            //获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTime;
            while (System.currentTimeMillis() < end) {
                //锁未获得
                if (jedis.setnx(lockKey,indentifier) == 1) {
                    jedis.expire(lockKey, lockExpire);
                    return indentifier;
                }
                //没有设置超时时间
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (JedisException e) {
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 释放锁
     * @param lockName 锁名称
     * @param indentifier 锁标示
     * @return 结果
     */
    public boolean releaseLock(String lockName,String indentifier){
        Jedis jedis = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            jedis = jedisPool.getResource();
            while (true) {
                //监视lock,准备开始事务
                jedis.watch(lockKey);
                if (indentifier.equals(jedis.get(lockKey))) {
                    Transaction multi = jedis.multi();
                    multi.del(lockKey);
                    List<Object> exec = multi.exec();
                    if (exec == null) {
                        continue;
                    }
                    retFlag = true;
                }
                jedis.unwatch();
                break;
            }
        } catch (JedisException e) {
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
}
