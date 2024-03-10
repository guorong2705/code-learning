package com.guorong.mvc.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JasyptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void jasyptTest() {
        // 加密
        System.out.println(stringEncryptor.encrypt("mapcfgood@123A"));
        System.out.println(stringEncryptor.decrypt("culTQ9mdZt6zyP7BIzp5G/XFigaCgImIIg5t/rAheItYO8RN1xHGyyoF2kEa6xXZ"));
    }


}
