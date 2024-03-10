package com.guorong.consumer.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * sentinel支持的feign
 */
@Component
public class OrderFeignClientFallBack implements OrderFeignClient {

    @Override
    public String getOrderNo(@PathVariable("orderNo") String orderNo) {
        return "order fall back";
    }
}
