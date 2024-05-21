package com.guorong.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guorong.demo.entity.DbStock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface DbStockMapper extends BaseMapper<DbStock> {

    @Select("select * from db_stock where id = #{id}")
    DbStock selectCustomById(@Param("id") Long id);

}
