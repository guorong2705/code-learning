package com.guorong.mybatis.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

import java.io.IOException;

import static com.guorong.mybatis.config.DatasourceSlaveConfig.SQL_SESSION_FACTORY_BEAN_NAME;

@Configuration
@MapperScan(basePackages = "com.guorong.mybatis.mapper.slave",
        sqlSessionFactoryRef = SQL_SESSION_FACTORY_BEAN_NAME)
public class DatasourceSlaveConfig {

    static final String SQL_SESSION_FACTORY_BEAN_NAME = "slaveSqlSessionFactory";

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    public DataSource slaveDataSource() {
        return slaveDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSourceInitializer slaveDataSourceInitializer() throws IOException {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(slaveDataSource());
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        // 设置要执行的脚本
        Resource[] resources  = new PathMatchingResourcePatternResolver().getResources("classpath:db/*-slave.sql");
        resourceDatabasePopulator.addScripts(resources);

        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        dataSourceInitializer.setEnabled(Boolean.TRUE);
        return dataSourceInitializer;
    }

    @Bean(SQL_SESSION_FACTORY_BEAN_NAME)
    public SqlSessionFactory slaveSqlSessionFactory(DatabaseIdProvider databaseIdProvider) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(slaveDataSource());
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);
        // MyBatis 映射器文件的位置
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/slave/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager slaveTransactionManager() {
        return new DataSourceTransactionManager(slaveDataSource());
    }


}
