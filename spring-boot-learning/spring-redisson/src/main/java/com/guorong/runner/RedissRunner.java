package com.guorong.runner;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.codec.CodecConfigurer;
import org.springframework.stereotype.Component;

@Component
public class RedissRunner implements ApplicationRunner {

    @Autowired
    private RedissonClient redissonClient;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        RBucket<String> key1 = redissonClient.getBucket("key1", StringCodec.INSTANCE);
        key1.set("0001");
    }
}
