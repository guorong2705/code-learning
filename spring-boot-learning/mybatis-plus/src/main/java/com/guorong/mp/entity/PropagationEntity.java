package com.guorong.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "propagation_entity", autoResultMap = true)
public class PropagationEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;
}
