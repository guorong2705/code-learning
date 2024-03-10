package com.guorong.springboot.jdbc.dao.impl;

import com.guorong.springboot.jdbc.dao.TacoDao;
import com.guorong.springboot.jdbc.entity.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guorong
 * @date 2020-05-18
 */
@Repository
public class TacoDaoImpl implements TacoDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TacoDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long insertTacoInfo(Taco taco) {
        // sql
        String sql = "insert into s04_taco(id, name) values(:id, :name)";
        // sql参数
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(taco);
        // 创建KeyHolder 用户保存数据库自动生成的主键
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // 执行sql
        jdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        // 返回主键
        return keyHolder.getKey().longValue();
    }


    @Override
    public void insertTacoIngredient(Long tacoId, List<String> ingredientIdList) {
        // sql
        String sql = "insert into s04_taco_ingredient(taco_id,ingredient_id) values(:tacoId,:ingredientId)";
        // sql参数
        MapSqlParameterSource[] sqlParameterSources = ingredientIdList
                .stream()
                .map(ingredientId -> {
                    return new MapSqlParameterSource()
                            .addValue("tacoId", tacoId)
                            .addValue("ingredientId", ingredientId);
                })
                .toArray(MapSqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, sqlParameterSources);
    }


}
