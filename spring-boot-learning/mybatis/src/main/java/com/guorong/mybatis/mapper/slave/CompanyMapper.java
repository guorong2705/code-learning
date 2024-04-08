package com.guorong.mybatis.mapper.slave;

import com.guorong.mybatis.entity.slave.Company;

import java.util.List;


public interface CompanyMapper {
    int insert(Company record);
    int insertSelective(Company record);
    List<Company> selectAll();
}