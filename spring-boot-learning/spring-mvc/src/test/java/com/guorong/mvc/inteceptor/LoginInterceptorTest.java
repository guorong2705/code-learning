package com.guorong.mvc.inteceptor;

import com.guorong.mvc.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginInterceptorTest {

    @LocalServerPort
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_FORMAT = "http://127.0.0.1:%s/login/interceptor/%s";

    @Test
    public void testGetUserInfoNoToken() {
        String url = String.format(URL_FORMAT, port, "getUserInfo");
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity(url, Person.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        Person body = responseEntity.getBody();
        log.info("statusCodeValue --->>> {}, body --->>> {}", statusCodeValue, body);
    }

    @Test
    public void testGetUserInfoHasToken() {
        String url = String.format(URL_FORMAT, port, "getUserInfo");
        // 设置请求头token
        HttpHeaders headers = new HttpHeaders();
        headers.set(LoginInterceptor.HEADER_TOKEN_KEY, "0001200013001");
        // 执行请求
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Person.class);
        // 打印请求结构
        int statusCodeValue = responseEntity.getStatusCodeValue();
        Person body = responseEntity.getBody();
        log.info("statusCodeValue --->>> {}, body --->>> {}", statusCodeValue, body);
    }

}
