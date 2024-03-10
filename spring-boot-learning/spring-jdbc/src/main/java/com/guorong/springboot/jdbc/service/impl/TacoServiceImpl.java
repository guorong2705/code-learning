package com.guorong.springboot.jdbc.service.impl;

import com.guorong.springboot.jdbc.dao.IngredientDao;
import com.guorong.springboot.jdbc.dao.TacoDao;
import com.guorong.springboot.jdbc.entity.Ingredient;
import com.guorong.springboot.jdbc.entity.Taco;
import com.guorong.springboot.jdbc.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guorong
 * @date 2020-05-18
 */
@Service
public class TacoServiceImpl implements TacoService {


    private final IngredientDao ingredientDao;

    private final TacoDao tacoDao;

    @Autowired
    public TacoServiceImpl(IngredientDao ingredientDao, TacoDao tacoDao) {
        this.ingredientDao = ingredientDao;
        this.tacoDao = tacoDao;
    }


    @Override
    public List<Ingredient> findAllIngredient() {
        return ingredientDao.selectAll();
    }


    @Override
    public Taco saveTaco(Taco taco) {
        // 保存设计的玉米饼到数据库
        Long tacoId = tacoDao.insertTacoInfo(taco);

        // 保存玉米饼和成分的关系
        tacoDao.insertTacoIngredient(tacoId, taco.getIngredients());

        // 返回插入的玉米饼
        taco.setId(tacoId);
        return taco;
    }
}
