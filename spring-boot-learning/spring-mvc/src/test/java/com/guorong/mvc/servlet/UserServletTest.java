package com.guorong.mvc.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServletTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private String port;

    private static final String URL_FORMAT = "http://127.0.0.1:%s/userServlet";

    @Test
    public void testDoPost() {
        String url = String.format(URL_FORMAT, port);
        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(url, null, JsonNode.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        log.info("statusCode --->>> {}, body --->>> {}", statusCodeValue, responseEntity.getBody());
    }

    @Test
    public void testDoGet() {
        String url = String.format(URL_FORMAT, port);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        log.info("statusCode --->>> {},  body --->>> {}", statusCodeValue, responseEntity.getBody());
    }

}
