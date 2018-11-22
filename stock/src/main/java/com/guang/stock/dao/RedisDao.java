package com.guang.stock.dao;

/**
 * @version V1.0
 * @author: LuoGuang
 * @Package com.guang.stock.dao
 * @Description:
 * @date: 2018/11/22 10:40
 */
public interface RedisDao {

    void set(String key, String value);
    String get(String key);
    void delete(String key);
}

