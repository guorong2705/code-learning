package com.guorong.validation.dto;

import com.guorong.validation.annotation.EnumValid;
import com.guorong.validation.enums.SizeEnum;
import com.guorong.validation.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @EnumValid(enumClass = SizeEnum.class, message = "22222222")
    @NotBlank(message = "用户名不能为空", groups = AddGroup.class)
    private String userName;

    @NotNull(message = "年龄不能空")
    @Range(min = 1, max = 80)
    private Integer age;

}
