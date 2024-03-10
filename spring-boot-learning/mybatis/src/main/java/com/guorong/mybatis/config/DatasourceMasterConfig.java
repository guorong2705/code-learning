package com.guorong.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.guorong.mybatis.typehandler.JsonNodeTypeHandler;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 主数据库配置类
 */
@MapperScan(basePackages = "com.guorong.mybatis.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
@Configuration
public class DatasourceMasterConfig {

    /**
     * mapper映射文件路径
     */
    private static final String LOCATION_PATTERN = "classpath*:mapper/master/*.xml";

    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Primary
    @Bean(name = "masterSqlSessionFactory")
    @ConfigurationPropertiesBinding
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource, DatabaseIdProvider databaseIdProvider) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(LOCATION_PATTERN));
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);
        // 注册 TypeHandler
        sqlSessionFactoryBean.setTypeHandlers(new JsonNodeTypeHandler());
        return sqlSessionFactoryBean.getObject();
    }
}
