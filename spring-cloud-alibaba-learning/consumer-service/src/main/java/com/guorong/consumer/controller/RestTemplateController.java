package com.guorong.consumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate负债均衡
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/")
public class RestTemplateController {

    private final RestTemplate restTemplate;

    @Value("${rest.server-url}")
    private String serverUrl;

    @GetMapping("restTemplate")
    public String restTemplate() {
        String url = serverUrl + "/"+ "rest/hello?name=guorong";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }




}
