package com.guorong.mybatis.mapper.master;


import com.guorong.mybatis.entity.master.TypeHandlerEntity;

import java.util.List;

public interface TypeHandlerEntityMapper {

    int insert(TypeHandlerEntity dto);

    List<TypeHandlerEntity> selectList();
}
