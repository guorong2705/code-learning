package com.guorong.mybatis.entity.master;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.io.Serializable;

@Data
public class TypeHandlerEntity implements Serializable {
    private Long id;
    private String name;
    private JsonNode dataJson;
}
