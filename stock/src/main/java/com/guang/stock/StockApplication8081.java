package com.guang.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock
 * @Description:
 * @date: 2018/11/22 9:48
 */
@SpringBootApplication
public class StockApplication8081 {
    
    public static void main(String[] args){
        SpringApplication.run(StockApplication8081.class, args);
    }


}
