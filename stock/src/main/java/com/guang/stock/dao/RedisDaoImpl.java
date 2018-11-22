package com.guang.stock.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.dao
 * @Description:
 * @date: 2018/11/22 10:41
 */
@Repository
public class RedisDaoImpl implements RedisDao{

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return  redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
