package com.guorong.springboot.jdbc.service;

import com.guorong.springboot.jdbc.entity.Ingredient;
import com.guorong.springboot.jdbc.entity.Taco;

import java.util.List;

/**
 * 玉米饼业务逻辑
 *
 * @author guorong
 * @date 2020-05-18
 */
public interface TacoService {

    /**
     * 查询玉米饼成分列表
     *
     * @return
     */
    List<Ingredient> findAllIngredient();


    /**
     * 保存玉米饼
     *
     * @param taco
     * @return
     */
    Taco saveTaco(Taco taco);
}
