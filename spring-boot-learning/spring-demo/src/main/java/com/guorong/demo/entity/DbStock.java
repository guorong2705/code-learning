package com.guorong.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "db_stock", autoResultMap = true)
public class DbStock {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "product_code")
    private String productCode;

    @TableField(value = "product_name")
    private String productName;

    @TableField(value = "count")
    private Long count;
}
