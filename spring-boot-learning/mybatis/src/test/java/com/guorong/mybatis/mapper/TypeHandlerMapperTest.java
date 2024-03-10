package com.guorong.mybatis.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guorong.mybatis.entity.TypeHandlerDto;
import com.guorong.mybatis.mapper.master.TypeHandlerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeHandlerMapperTest {

    @Autowired
    private TypeHandlerMapper typeHandlerMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        // 01
        TypeHandlerDto typeHandlerDto01 = new TypeHandlerDto();
        typeHandlerDto01.setName("张三-01");
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("key-01", "value-01");
        objectNode.put("key-02", "value-02");
        typeHandlerDto01.setDataJson(objectNode);
        typeHandlerMapper.insert(typeHandlerDto01);
        // 02
        TypeHandlerDto typeHandlerDto02 = new TypeHandlerDto();
        typeHandlerDto02.setName("张三-01");
        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("0001");
        arrayNode.add(12);
        typeHandlerDto02.setDataJson(arrayNode);
        typeHandlerMapper.insert(typeHandlerDto02);
    }

    @Test
    public void test() {
        List<TypeHandlerDto> typeHandlerDtoList = typeHandlerMapper.selectList();
        typeHandlerDtoList.forEach(dto -> log.info(dto.toString()));
    }

}
