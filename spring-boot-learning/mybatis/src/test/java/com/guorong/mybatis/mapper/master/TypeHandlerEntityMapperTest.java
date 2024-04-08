package com.guorong.mybatis.mapper.master;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guorong.mybatis.entity.master.TypeHandlerEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class TypeHandlerEntityMapperTest {

    @Autowired
    private TypeHandlerEntityMapper typeHandlerEntityMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        TypeHandlerEntity typeHandler01 = new TypeHandlerEntity();
        typeHandler01.setName("张三");
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("key-01", "value-01");
        objectNode.put("key-02", "value-02");
        typeHandler01.setDataJson(objectNode);
        typeHandlerEntityMapper.insert(typeHandler01);
    }

    @Test
    public void test() {
        List<TypeHandlerEntity> typeHandlerList = typeHandlerEntityMapper.selectList();
        typeHandlerList.forEach(dto -> log.info(dto.toString()));
    }

}
