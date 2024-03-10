package com.guorong.mybatis.mapper.master;

import com.guorong.mybatis.entity.TypeHandlerDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeHandlerMapper {

    int insert(TypeHandlerDto dto);

    List<TypeHandlerDto> selectList();
}
