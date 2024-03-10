package com.guorong.mvc.servlet;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebServlet(name = "userServlet", urlPatterns = "/servlet/userServlet", loadOnStartup = 1)
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("{} --->>> doGet", getClass().getName());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("12222".getBytes());
        response.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("{} --->>> doPost", getClass().getName());
        // 设置响应体为JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 响应体内容
        ObjectNode classObjectNode = JsonNodeFactory.instance.objectNode()
                .put("className", "三年二班")
                .put("userCount", 45);
        ObjectNode personObjectNode = JsonNodeFactory.instance
                .objectNode()
                .put("name", "张三")
                .put("age", 25)
                .putPOJO("class", classObjectNode);
        // 设置响应体
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(personObjectNode.toString().getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();
    }
}
