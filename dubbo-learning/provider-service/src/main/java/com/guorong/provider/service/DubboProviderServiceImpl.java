package com.guorong.provider.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@DubboService(interfaceClass = DubboProviderService.class)
public class DubboProviderServiceImpl implements DubboProviderService{

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public String sayHello(String name) {
        return String.format("%s %s say hello %s", serviceName, serverPort, name);
    }
}
