package com.guorong.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.guorong.mp.enums.DeleteStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class BaseEntity implements Serializable {

    @Version // 乐观锁注解
    private Integer version = 1;

    @TableLogic(value = "0", delval = "1") // 逻辑删除
    @TableField(fill = FieldFill.INSERT)
    private DeleteStatusEnum deleteFlag;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
