package com.guorong.redis;

import com.guorong.redis.entity.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author guorong
 * @date 2020-05-13
 */
@Slf4j
@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 测试读写分离
    @Test
    public void testReadWriteDiff() {
        stringRedisTemplate.opsForValue().set("张三", "1111");
        log.info("============ 写入成功 ==========================");
        stringRedisTemplate.opsForValue().get("张三");
        log.info("============ 读取成功 ==========================");
    }


    //==================================字符串操作==================================================
    @Test
    public void testSetString01() {
        // 设置的值为永久的
        redisTemplate.opsForValue().set("hello01", "world");
    }

    // 操作字符串建议
    @Test
    public void testSetString02() {
        // 设置的值为永久的
        stringRedisTemplate.opsForValue().set("58998", "world");
    }

    @Test
    public void test() {
        BookDto book = new BookDto();
        book.setId("001");
        book.setBookName("Redis从入门到精通");
        book.setPrice(Double.valueOf(25.6));
        // 存入redis中
        redisTemplate.opsForValue().set("book", book);
    }

    // 设置字符串失效时间
    @Test
    public void testSetStringTimeout() {
        // 设置10秒后失效
        stringRedisTemplate.opsForValue().set("hello", "world", 20, TimeUnit.SECONDS);
    }

    // 获取数据
    @Test
    public void testGet() {
        String value = stringRedisTemplate.opsForValue().get("hello");
        log.info(value);
    }


    //===================================操作 List==========================================================

    @Test
    public void testSetList() {
        // 设置集合
        Long count = redisTemplate
                .opsForList()
                .leftPushAll("numberList", "1", "2", "3", "4", "5", "6");

        log.info(String.valueOf(count));
    }

    @Test
    public void testGetList() {
        List<Integer> numberList = redisTemplate.opsForList().range("numberList", 0, 2);
        log.info(numberList.toString());
    }




}
