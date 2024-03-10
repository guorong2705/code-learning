package com.guorong.mp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@MapperScan("com.guorong.mp.mapper")
public class MybatisPlusConfig {

    /**
     * 注入 mybatis-plus 拦截器
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // mybatis-plus 分页拦截器
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor());
        // 乐观锁拦截器
        mybatisPlusInterceptor.addInnerInterceptor(optimisticLockerInterceptor());
        // 动态表名拦截器
        mybatisPlusInterceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    /**
     * 注入 mybatis-plus分页拦截器
     *
     * @return
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }

    /**
     * 注入 mybatis-plus 乐观锁拦截器
     *
     * @return
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 注入 mybatis-plus 动态表名拦截器
     *
     * @return
     */
    @Bean
    public DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor() {
        List<String> dynamicTableList = Arrays.asList("t_dynamic_table");
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        // 修改表名称
        dynamicTableNameInnerInterceptor.setTableNameHandler((String sql, String tableName) -> {
            if (dynamicTableList.contains(tableName)) {
                tableName = tableName + "_2023";
            }
            return tableName;
        });
        return dynamicTableNameInnerInterceptor;
    }

}
