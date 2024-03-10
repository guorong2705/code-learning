package com.guorong.mybatis.mapper.slave;

import com.guorong.mybatis.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper {

    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectAll();
}