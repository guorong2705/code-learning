package com.guorong.mvc.rest_template;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String urlFormat = "http://localhost:%s/restTemplate/mock/%s";


    @Test
    public void testPing() {
        String url = String.format(urlFormat, port, "ping");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        log.info("statusCodeValue --->>> {}, body --->>> {}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
    }


    @Test
    public void testJsonNode() {
        String url = String.format(urlFormat, port, "/jsonNode/param");
        // 请求参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "guorong");
        paramMap.put("password", "123456");
        // 方法1 --->>>
        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(url, paramMap, JsonNode.class);
        log.info("statusCodeValue --->>> {}, body --->>> {}", responseEntity.getStatusCodeValue(), responseEntity.getBody().toString());
        // 方法2 --->>>
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(url, paramMap, String.class);
        log.info("statusCodeValue --->> {}, body --->>> {}", responseEntityStr.getStatusCodeValue(), responseEntityStr.getBody());
    }


    @Test
    public void testExchange() {
        String url = String.format(urlFormat, port, "/jsonNode/param");
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", "021364546454");
        // 请求体
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "guorong");
        paramMap.put("password", "123456");
        // 执行请求
        HttpEntity<Map> httpEntity = new HttpEntity<>(paramMap, headers);
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, JsonNode.class);
        log.info("statusCodeValue --->>> {}, body --->>> {}", responseEntity.getStatusCodeValue(), responseEntity.getBody().toPrettyString());
    }


    @Test
    public void testRequestHeader() {
        String url = String.format(urlFormat, port, "/requestHeader");
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "00000012361");
        headers.set("password", "123456");
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        // 请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        log.info("statusCodeValue --->> {}, body --->>> {}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
    }


    @Test
    public void testRequestParam() {
        // 方法1: 推荐使用(传参替换使用 {?} 来表示坑位，根据实际的传参顺序来填充)
        String url = String.format(urlFormat, port, "/requestParam?userName={?}&password={?}");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, "张三", "123456");
        log.info("statusCodeValue --->> {}, body --->>> {}", responseEntity.getStatusCodeValue(), responseEntity.getBody());

        // 方法2：使用{xx}来传递参数时，这个xx对应的就是 map 中的 key
        url = String.format(urlFormat, port, "/requestParam?userName={userName}&password={password}");
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("userName", "王力宏");
        paramMap.put("password", "123456789");
        responseEntity = restTemplate.getForEntity(url, String.class, paramMap);
        log.info("statusCodeValue --->> {}, body --->>> {}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
    }


    @Test
    public void testForObject() {
        String url = String.format(urlFormat, port, "/jsonNode/param");
        // JSON请求参数
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("name", "guorong");
        objectNode.put("password", "123456");
        String body = restTemplate.postForObject(url, objectNode, String.class);
        log.info("body --->>> {}", body);
    }

}
