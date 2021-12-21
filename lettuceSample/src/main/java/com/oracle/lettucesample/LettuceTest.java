/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.lettucesample;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 *
 * @author pravisin
 */
public class LettuceTest {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://cagbu-mum-9.snbomprshared1.gbucdsint02bom.oraclevcn.com:6379/");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("key", "Hello, Redis!");
        String value = syncCommands.get("key");
        System.out.println(value);
           
    }

}
