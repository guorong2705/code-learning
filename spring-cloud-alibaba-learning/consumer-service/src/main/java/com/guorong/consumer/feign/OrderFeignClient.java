package com.guorong.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-service", path = "/feign", fallback = OrderFeignClientFallBack.class)
public interface OrderFeignClient {

    @GetMapping("/getOrderNo/{orderNo}")
    String getOrderNo(@PathVariable("orderNo") String orderNo);
}
