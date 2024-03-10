package com.guorong.validation.dto;

import com.guorong.validation.annotation.EnumValid;
import com.guorong.validation.enums.SizeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class CustomerDto implements Serializable {

    @Size(message = "集合长度不能小于{min}", min = 1)
    private List<@Length(min = 3, message = "元素长度必须大于等于{min}") String> productList;

    @NotBlank(message = "不能为空")
    @EnumValid(enumClass = SizeEnum.class)
    private String clothSize;

    @Range(min = 1, max = 3, message = "数字范围[{min},{max}]")
    @NotNull(message = "不能为空")
    private Integer number;

    @Valid
    private UserDto user;
}
