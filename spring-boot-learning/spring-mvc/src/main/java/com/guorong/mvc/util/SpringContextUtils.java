package com.guorong.mvc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    @Override
    public void destroy() {
        log.info("clear SpringContextHolder inner --->>> {}", applicationContext);
        applicationContext = null;
    }

    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requireType) {
        return applicationContext.getBean(requireType);
    }

    public static <T> T getBean(String beanName, Class<T> requireClass) {
        return applicationContext.getBean(beanName, requireClass);
    }

    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }


}
