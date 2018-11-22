package com.guang.stock.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.config
 * @Description:
 * @date: 2018/11/22 11:03
 */
@EnableAutoConfiguration
public class RedisConfig {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean
    public RedisTemplate<String, ?> redisTemplate() {
        RedisTemplate<String, ?> template = new RedisTemplate();
        return template;
    }
}
