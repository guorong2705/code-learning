package com.guorong.mybatis.mapper;

import com.guorong.mybatis.entity.Company;
import com.guorong.mybatis.mapper.slave.CompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyMapperTests {

    @Autowired
    private CompanyMapper companyMapper;


    @Test
    public void testSelectAll() {
        List<Company> companyList = companyMapper.selectAll();
        companyList.stream().forEach(company -> log.info(company.toString()));
    }

}
