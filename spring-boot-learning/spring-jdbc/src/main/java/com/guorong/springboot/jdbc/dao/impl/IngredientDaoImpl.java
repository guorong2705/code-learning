package com.guorong.springboot.jdbc.dao.impl;

import com.guorong.springboot.jdbc.dao.IngredientDao;
import com.guorong.springboot.jdbc.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author guorong
 * @date 2020-05-17
 */
@Repository
public class IngredientDaoImpl implements IngredientDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 定义函数接口实现(数据库行映射到对象)
     */
    private RowMapper<Ingredient> rowMapper = (ResultSet rs, int rowNum) -> {
        return new Ingredient(rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    };


    @Override
    public List<Ingredient> selectAll() {
        // sql
        String sql = "select id,name,type from s04_ingredient";
        // 执行查询
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public Ingredient selectById(String id) {
        // sql
        String sql = "select id,name,type from s04_ingredient where id=?";
        // 执行查询
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    @Override
    public Ingredient insert(Ingredient ingredient) {
        // sql
        String sql = "insert into s04_ingredient(id,name,type) values(?,?,?)";
        // 执行插入
        jdbcTemplate.update(sql, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());

        return ingredient;
    }
}
