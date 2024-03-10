package com.guorong.validation.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class StudentDto implements Serializable {

    @NotBlank(message = "姓名不能为空")
    @Length(min = 1, max = 3, message = "长度介于{min}到{max}之间")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "不能小于{value}")
    private Integer age;
}
