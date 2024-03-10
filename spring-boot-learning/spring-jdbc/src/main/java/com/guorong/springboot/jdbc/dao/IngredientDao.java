package com.guorong.springboot.jdbc.dao;

import com.guorong.springboot.jdbc.entity.Ingredient;

import java.util.List;

/**
 * 玉米饼成分 持久层
 *
 * @author guorong
 * @date 2020-05-17
 */
public interface IngredientDao {


    /**
     * 查询所有成分列表
     *
     * @return
     */
    List<Ingredient> selectAll();

    /**
     * 根据主键查询
     *
     * @param id 成分主键
     * @return
     */
    Ingredient selectById(String id);


    /**
     * 插入数据
     *
     * @param ingredient
     * @return
     */
    Ingredient insert(Ingredient ingredient);


}
