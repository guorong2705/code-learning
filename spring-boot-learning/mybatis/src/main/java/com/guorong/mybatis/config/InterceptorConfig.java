package com.guorong.mybatis.config;

import com.guorong.mybatis.interceptor.MyInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {

    @Bean
    public MyInterceptor addPlugin(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        MyInterceptor myInterceptor = new MyInterceptor();
        sqlSessionFactory.getConfiguration().addInterceptor(myInterceptor);
        return myInterceptor;
    }
}
