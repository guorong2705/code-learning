package com.guorong.mvc.rest_template;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guorong.mvc.rest_template.vo.RestTemplateReqVo;
import com.guorong.mvc.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/restTemplate/mock/")
public class RestTemplateController {

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("jsonNode/param")
    public JsonNode jsonNode(@RequestBody RestTemplateReqVo param) {
        if ("guorong".equals(param.getName()) && "123456".equals(param.getPassword())) {
            ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
            objectNode.put("userId", "2001");
            objectNode.put("name", "张三");
            objectNode.put("age", 20);
            return objectNode;
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }


    @PostMapping("requestHeader")
    public String requestHeader() {
        HttpServletRequest request = RequestUtils.getHttpServletRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        log.info("打印请求头 --------->>>");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = request.getHeader(headerName);
            log.info("headerName --->>> {}, value --->> {}", headerName, value);
        }
        return "打印请求头";
    }


    @GetMapping("requestParam")
    public String requestParam(@RequestParam("userName") String userName,
                               @RequestParam("password") String password) {
        log.info("url --->>> {}", RequestUtils.getHttpServletRequest().getRequestURI());
        return "返回：" + userName + "--" + password;
    }

}
