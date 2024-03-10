package com.guorong.mybatis.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class TypeHandlerDto implements Serializable {

    private Long id;

    private String name;

    private JsonNode dataJson;
}
