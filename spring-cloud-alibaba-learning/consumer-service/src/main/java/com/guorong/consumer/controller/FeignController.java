package com.guorong.consumer.controller;

import com.guorong.consumer.feign.OrderFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feign/")
public class FeignController {

    private final OrderFeignClient orderFeignClient;

    @GetMapping("getOrderNo")
    public String getOrderNo() {
        String uuid = UUID.randomUUID().toString();
        return orderFeignClient.getOrderNo(uuid);
    }

}
