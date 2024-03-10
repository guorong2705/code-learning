package com.guorong.serizer;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 针对RegionDto对象的JSON序列化和反序列化器
 */
@JsonComponent
public class RegionDtoJsonComponent {

    public static class RegionDtoJsonDeserializer extends JsonDeserializer<RegionDto> {
        @Override
        public RegionDto deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            TreeNode treeNode = jsonParser.readValueAsTree();
            // 入参json是个javaBean
            if (treeNode instanceof ObjectNode) {
                ObjectNode objectNode = (ObjectNode) treeNode;
                RegionDto regionDto = new RegionDto();
                regionDto.setProvinceCode(objectNode.get("provinceCode").asText());
                regionDto.setCityCode(objectNode.get("cityCode").asText());
                regionDto.setCountyCode(objectNode.get("countyCode").asText());
                return regionDto;
            }
            // 入参json是个数组
            if (treeNode instanceof ArrayNode) {
                ArrayNode arrayNode = (ArrayNode) treeNode;
                if (CollectionUtil.isEmpty(arrayNode)) {
                    return null;
                }
                RegionDto regionDto = new RegionDto();
                for (int i = 0; i < arrayNode.size(); i++) {
                    if (i == 0) {
                        regionDto.setProvinceCode(arrayNode.get(0).asText());
                    }
                    else if (i==1) {
                        regionDto.setCityCode(arrayNode.get(1).asText());
                    }
                    else if (i == 2) {
                        regionDto.setCountyCode(arrayNode.get(2).asText());
                    }
                }
                return regionDto;
            }
            throw  new IllegalArgumentException("RegionDto 对应的json格式无法解析");
        }
    }

    public static class RegionDtoJsonSerializer extends JsonSerializer<RegionDto> {
        @Override
        public void serialize(RegionDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            List<String> list = new ArrayList<>();
            if (StrUtil.isNotBlank(value.getProvinceCode())) {
                list.add(value.getProvinceCode());
            }
            if (StrUtil.isNotBlank(value.getCityCode())) {
                list.add(value.getCityCode());
            }
            if (StrUtil.isNotBlank(value.getCountyCode())) {
                list.add(value.getCountyCode());
            }
            gen.writeObject(list);
        }
    }

}

