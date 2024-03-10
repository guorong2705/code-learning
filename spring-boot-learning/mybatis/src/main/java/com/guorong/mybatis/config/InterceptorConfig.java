package com.guorong.mybatis.config;

import com.guorong.mybatis.interceptor.MyInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisInterceptorConfig
 * @Description: TODO
 * @Author: 89019586
 * @Date: 2022/9/13 11:06
 * Copyright(C),2019-2022,丰图科技有限公司
 */
@Configuration
public class InterceptorConfig {

    @Bean
    public MyInterceptor addPlugin(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        MyInterceptor myInterceptor = new MyInterceptor();
        sqlSessionFactory.getConfiguration().addInterceptor(myInterceptor);
        return myInterceptor;
    }
}
