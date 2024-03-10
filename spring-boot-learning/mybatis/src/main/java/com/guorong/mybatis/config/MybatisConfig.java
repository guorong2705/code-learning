package com.guorong.mybatis.config;

import com.guorong.mybatis.entity.Company;
import com.guorong.mybatis.entity.Order;
import com.guorong.mybatis.entity.OrderItem;
import com.guorong.mybatis.entity.User;
import com.guorong.mybatis.mapper.master.OrderMapper;
import com.guorong.mybatis.mapper.master.UserMapper;
import com.guorong.mybatis.mapper.slave.CompanyMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

@Configuration
public class MybatisConfig {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("MySQL", "mysql");
        p.setProperty("PostgreSQL", "postgresql");
        p.setProperty("DB2", "db2");
        p.setProperty("H2", "h2");
        p.setProperty("SQL Server", "sqlserver");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }


    @Bean
    public CommandLineRunner initH2Data() {
        return args -> {
            // 用户数据
            userMapper.insertUser(new User(null, "zhangshang", "张三", "123456", 22, LocalDateTime.now(), LocalDateTime.now()));
            userMapper.insertUser(new User(null, "nisi", "李四", "123456", 23, LocalDateTime.now(), LocalDateTime.now()));
            userMapper.insertUser(new User(null, "wangwu", "王五", "123456", 24, LocalDateTime.now(), LocalDateTime.now()));
            // 公司数据
            companyMapper.insert(new Company(null,"小米", "深圳宝安"));
            companyMapper.insert(new Company(null,"腾讯", "深圳南山"));
            companyMapper.insert(new Company(null,"华为", "深圳龙华"));
        };
    }
}
