package com.guorong.validation.param_validate;

import com.guorong.common.ApiResult;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Validated
@RestController
@RequestMapping("/paramValidate/")
public class ParamValidateController {

    @PostMapping("validateJson")
    public ApiResult validateJson(@Validated @RequestBody StudentDto student) {
        return ApiResult.success("校验@RequestBody");
    }

    @GetMapping("validatePathVariable/{name}/{age}")
    public ApiResult validatePathVariable(@Size(max = 3, message = "长度不能大于{max}") @PathVariable("name") String name,
                                          @Max(value = 20, message = "最大值为{value}") @PathVariable("age") Integer age) {
        return ApiResult.success("校验路径参数");
    }

    @PostMapping("validateRequestParam")
    public ApiResult validateRequestParam(@Size(max = 3, message = "长度不能大于{max}") @RequestParam("name") String name,
                                          @Max(value = 20, message = "最大值为{value}") @RequestParam("age") Integer age) {
        return ApiResult.success("校验表单参数");
    }


    @Data
    public static class StudentDto implements Serializable {
        @NotEmpty(message = "姓名不能为空")
        @Length(min = 1, max = 3, message = "长度介于{min} 到 {max}之间")
        private String name;

        @NotNull(message = "年龄不能为空")
        @Min(value = 0, message = "不能小于 {value}")
        private Integer age;
    }


}
