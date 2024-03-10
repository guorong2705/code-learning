package com.guorong.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dynamic_table")
public class DynamicTable {

    private Long id;

    private String name;

}
