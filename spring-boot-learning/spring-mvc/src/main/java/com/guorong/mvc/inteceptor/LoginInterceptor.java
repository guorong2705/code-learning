package com.guorong.mvc.inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.Map;
import java.util.Objects;

/**
 * 登录拦截器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public static final String HEADER_TOKEN_KEY = "token";


    /**
     * 在请求处理之前进行调用（Controller方法调用之前）,返回true才会继续执行，返回false则取消当前请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod hm = (HandlerMethod) handler;
        IsLogin isLoginAnnotation = hm.getMethodAnnotation(IsLogin.class);
        // 判断是否需要登录认证
        if (Objects.isNull(isLoginAnnotation) || !isLoginAnnotation.isLogin()) {
            return true;
        }
        String token = request.getHeader(HEADER_TOKEN_KEY);
        Assert.notNull(token, "请求头缺少token");

        log.info("{} --->>> {}", HEADER_TOKEN_KEY, token);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    /**
     * 在整个请求结束之后被调用，也就是在 DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
