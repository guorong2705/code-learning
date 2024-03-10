package com.guorong.mvc.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebFilter(urlPatterns = "/servlet/*")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 包装响应体
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("{} --->>> doFilter--before", getClass().getName());
        chain.doFilter(request, responseWrapper);
        log.info("{} --->>> doFilter--after", getClass().getName());

        log.info("打印响应体 --->>> {}", new String(responseWrapper.getContentAsByteArray()));

        // 将正文复制到响应
        responseWrapper.copyBodyToResponse();
    }
}
