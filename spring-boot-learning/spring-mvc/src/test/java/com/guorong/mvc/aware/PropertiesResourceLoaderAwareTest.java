package com.guorong.mvc.aware;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PropertiesResourceLoaderAwareTest {

    @Autowired
    private  PropertiesResourceLoaderAware propertiesResourceLoaderAware;

    @Test
    public void test() {
        log.info("properties --->> {}", propertiesResourceLoaderAware.getProperties());
    }


}
