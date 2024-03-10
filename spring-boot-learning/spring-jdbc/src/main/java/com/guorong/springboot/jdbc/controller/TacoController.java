package com.guorong.springboot.jdbc.controller;

import com.guorong.springboot.jdbc.entity.Ingredient;
import com.guorong.springboot.jdbc.entity.Order;
import com.guorong.springboot.jdbc.entity.Taco;
import com.guorong.springboot.jdbc.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author guorong
 * @date 2020-05-18
 */
@Controller
@RequestMapping("/taco/")
@SessionAttributes("order")
public class TacoController {

    private final TacoService tacoService;

    @Autowired
    public TacoController(TacoService tacoService) {
        this.tacoService = tacoService;
    }


    @ModelAttribute(name = "order")
    public Order order() {
        Order order = new Order();
        order.setId(20001L);
        return order;
    }


    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    /**
     * 显示玉米饼表单
     *
     * @param model
     * @return
     */
    @GetMapping("showTacoForm")
    public String showTacoForm(Model model) {
        // 获取玉米饼成分列表
        List<Ingredient> ingredientList = tacoService.findAllIngredient();
        // 根据成分类型分类
        for (Ingredient.Type type : Ingredient.Type.values()) {
            // 获取相同类型的成分
            List<Ingredient> typeList = ingredientList
                    .stream()
                    .filter(i -> Objects.equals(type, i.getType()))
                    .collect(Collectors.toList());
            // 存入模型
            model.addAttribute(type.toString().toLowerCase(), typeList);
        }

        return "taco-form";
    }


    /**
     * 保存设计的玉米饼
     *
     * @param taco
     * @return
     */
    @PostMapping("/save")
    public String saveTaco(@Valid Taco taco, Errors errors,
                           @SessionAttribute("order") Order order) {

        if (errors.hasErrors()) {
            // 提交表单信息存在错误，返回表单页面
            return "taco-form";
        }

        // 保存设计的玉米饼
        Taco savedTaco = tacoService.saveTaco(taco);
        // 将玉米饼存入到订单中
        order.getTacoList().add(savedTaco);

        // 到订单表单页面
        return "order-form";

    }

}
