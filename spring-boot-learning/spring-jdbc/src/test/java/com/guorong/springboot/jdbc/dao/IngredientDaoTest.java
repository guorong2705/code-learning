package com.guorong.springboot.jdbc.dao;

import com.guorong.springboot.jdbc.entity.Ingredient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author guorong
 * @date 2020-05-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientDaoTest {

    @Autowired
    private IngredientDao ingredientDao;

    @Test
    public void testSelectAll() {
        List<Ingredient> ingredients = ingredientDao.selectAll();
        Assert.assertNotNull(ingredients);
        Assert.assertFalse(ingredients.isEmpty());
    }


    @Test
    public void testSelectById() {
        String ingredientId = "FLTO";
        Ingredient ingredient = ingredientDao.selectById(ingredientId);
        Assert.assertNotNull(ingredient);
        Assert.assertEquals(ingredientId, ingredient.getId());
    }


}
