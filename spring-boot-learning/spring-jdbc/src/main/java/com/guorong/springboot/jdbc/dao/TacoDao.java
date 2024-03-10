package com.guorong.springboot.jdbc.dao;

import com.guorong.springboot.jdbc.entity.Taco;

import java.util.List;

/**
 * 玉米饼 持久层
 *
 * @author guorong
 * @date 2020-05-18
 */
public interface TacoDao {

    /**
     * 保存玉米饼信息，并返回数据库自动生成的主键
     *
     * @param taco
     * @return 数据库生成的组件
     */
    Long insertTacoInfo(Taco taco);

    /**
     * 关联玉米饼和成分的关系
     *
     * @param tacoId
     * @param ingredientIdList
     */
    void insertTacoIngredient(Long tacoId, List<String> ingredientIdList);


}
