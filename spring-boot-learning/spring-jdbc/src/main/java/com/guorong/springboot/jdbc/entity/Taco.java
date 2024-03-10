package com.guorong.springboot.jdbc.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 玉米饼
 *
 * @author guorong
 * @date 2020-05-14
 */
@Data
public class Taco {

    /**
     * 主键
     */
    private Long id;

    /**
     * 玉米饼的名称
     */
    @NotNull
    @Size(min = 5, message = "玉米饼的名称至少5个字符")
    private String name;

    /**
     * 玉米饼的成分
     */
    @Size(min = 1, message = "请至少选择一种玉米饼的成分")
    private List<String> ingredients = new ArrayList<>();

    /**
     * 创建日期和时间
     */
    private Date createdTime;
}
