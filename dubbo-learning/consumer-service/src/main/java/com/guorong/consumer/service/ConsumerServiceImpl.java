package com.guorong.consumer.service;

import com.guorong.provider.service.DubboProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService{

    @DubboReference
    private DubboProviderService dubboProviderService;

    @Override
    public String sayHello(String name) {
        return dubboProviderService.sayHello(name);
    }
}
