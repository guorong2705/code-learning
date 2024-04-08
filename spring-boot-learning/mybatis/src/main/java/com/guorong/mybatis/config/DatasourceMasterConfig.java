package com.guorong.mybatis.config;

import com.guorong.mybatis.typehandler.JsonNodeTypeHandler;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@MapperScan(basePackages = "com.guorong.mybatis.mapper.master",
        sqlSessionFactoryRef = DatasourceMasterConfig.SQL_SESSION_FACTORY_BEAN_NAME)
public class DatasourceMasterConfig {

    static final String SQL_SESSION_FACTORY_BEAN_NAME = "masterSqlSessionFactory";

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource masterDataSource() {
        return masterDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSourceInitializer masterDataSourceInitializer() throws IOException {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(masterDataSource());
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        // 设置要执行的脚本
        Resource[] resources  = new PathMatchingResourcePatternResolver().getResources("classpath:db/*-master.sql");
        resourceDatabasePopulator.addScripts(resources);

        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        dataSourceInitializer.setEnabled(Boolean.TRUE);
        return dataSourceInitializer;
    }


    @Bean(value = SQL_SESSION_FACTORY_BEAN_NAME)
    public SqlSessionFactory masterSqlSessionFactory(DatabaseIdProvider databaseIdProvider) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDataSource());
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);
        // MyBatis 映射器文件的位置
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/master/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        // TypeHandler
        sqlSessionFactoryBean.setTypeHandlers(new JsonNodeTypeHandler());
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }


}
