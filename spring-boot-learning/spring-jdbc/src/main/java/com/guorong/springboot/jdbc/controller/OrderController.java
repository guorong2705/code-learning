package com.guorong.springboot.jdbc.controller;

import com.guorong.springboot.jdbc.entity.Order;
import com.guorong.springboot.jdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author guorong
 * @date 2020-05-18
 */
@Controller
@RequestMapping("/order/")
@SessionAttributes("order")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * 保存订单
     *
     * @param order
     * @param errors
     * @param session
     * @return
     */
    @PostMapping("save")
    public String saveOrder(@Valid Order order, Errors errors,
                            HttpSession session) {

        if (errors.hasErrors()) {
            // 表单校验失败，返回到提交订单表单页面重新填写
            return "order-form";
        }
        // 保存玉米饼订单
        orderService.saveOrder(order);
        // 移除会话中的属性
        session.removeAttribute("order");
        // 去首页
        String toHomePage = "redirect:/";
        return toHomePage;
    }


}
