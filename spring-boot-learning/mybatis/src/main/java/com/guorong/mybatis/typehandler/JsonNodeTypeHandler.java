package com.guorong.mybatis.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonNodeTypeHandler extends BaseTypeHandler<JsonNode> {

    private final ObjectMapper objectMapper;

    public JsonNodeTypeHandler() {
        objectMapper = new ObjectMapper();
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonNode parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public JsonNode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonNodeStr = rs.getString(columnName);
        try {
            return objectMapper.readTree(jsonNodeStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public JsonNode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonNodeStr = rs.getString(columnIndex);
        try {
            return objectMapper.readTree(jsonNodeStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public JsonNode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        try {
            return objectMapper.readTree(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
