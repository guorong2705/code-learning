package com.guorong.mybatis.mapper.slave;

import com.guorong.mybatis.entity.slave.Company;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class CompanyMapperTests {

    @Autowired
    private CompanyMapper companyMapper;

    @BeforeEach
    public void init() {
        Company company1 = new Company(101, "腾讯", "深圳");
        Company company2 = new Company(102, "百度", "深圳");
        companyMapper.insert(company1);
        companyMapper.insert(company2);
    }


    @Test
    public void testSelectAll() {
        List<Company> companyList = companyMapper.selectAll();
        companyList.stream().forEach(company -> log.info(company.toString()));
    }

}
