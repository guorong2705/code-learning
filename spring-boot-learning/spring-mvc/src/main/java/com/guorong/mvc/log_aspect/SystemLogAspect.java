package com.guorong.mvc.log_aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guorong.mvc.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author guorong
 * @date 2021-06-05
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SystemLogAspect {

    private final ObjectMapper objectMapper;

    // 定义切点
    @Pointcut("@annotation(com.guorong.mvc.log_aspect.SystemLog)")
    public void pointCut() {}

    @SneakyThrows
    @Around("pointCut() && @annotation(systemLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, SystemLog systemLog) {
        // 方法执行时间
        long timeBegin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeEnd = System.currentTimeMillis();
        log.info("method execute millisecond --->>> {}", (timeEnd - timeBegin));

        // 注解属性值
        HttpServletRequest request = RequestUtils.getHttpServletRequest();
        String firstModule = systemLog.firstModule();  // 一级模块
        String secondModule = systemLog.secondModule();// 二级模块
        String actionType = systemLog.actionType();    // 操作类型
        log.info("firstModule --->>> {}, secondModule --->>> {}, actionType --->>> {}", firstModule, secondModule, actionType);

        // 请求参数
        Object requestParam = getRequestParam(joinPoint);
        log.info("request param --->>> {}", objectMapper.writeValueAsString(requestParam));

        // ip地址
        String ipaddress = RequestUtils.getIpAddress();
        log.info("ip address --->>> {}", ipaddress);

        // 请求uri
        String uri = request.getRequestURI();
        log.info("url --->>> {}", uri);

        return result;
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint
     */
    private Object getRequestParam(JoinPoint joinPoint) {
        List<Object> paramList = new ArrayList<>();
        Object[] argsArray = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); // 获取方法签名
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // @RequestBody 注解的参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (Objects.nonNull(requestBody)) {
                paramList.add(argsArray[i]);
            }
            // @RequestParam 注解的参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (Objects.nonNull(requestParam)) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                Object value = argsArray[i];
                // 判断RequestParam注解是否指定字段名称
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, value);
                paramList.add(map);
            }
            // @PathVariable 注解参数
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            if (Objects.nonNull(pathVariable)) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                Object value = argsArray[i];
                // 判断 PathVariable 注解是否指定字段名称
                if (!StringUtils.isEmpty(pathVariable.value())) {
                    key = pathVariable.value();
                }
                map.put(key, value);
                paramList.add(map);
            }
        }
        // 返回参数数组
        if (paramList.isEmpty()) {
            return null;
        }
        if (paramList.size() == 1) {
            return paramList.get(0);
        }
        return paramList;
    }

}
