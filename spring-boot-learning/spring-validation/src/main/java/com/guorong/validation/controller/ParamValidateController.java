package com.guorong.validation.controller;

import com.guorong.common.ApiResult;
import com.guorong.validation.dto.StudentDto;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@Validated
@RestController
@RequestMapping("/paramValidate/")
public class ParamValidateController {

    @PostMapping("validateJson")
    public ApiResult validateJson(@Validated @RequestBody StudentDto student) {
        return ApiResult.success("校验@RequestBody");
    }

    @GetMapping("validatePathVariable/{name}/{age}")
    public ApiResult validatePathVariable(@Length(max = 3, message = "长度不能大于{max}") @PathVariable("name") String name,
                                          @Max(value = 20, message = "最大值为{value}") @PathVariable("age") Integer age) {
        return ApiResult.success("校验路径参数");
    }

    @PostMapping("validateRequestParam")
    public ApiResult validateRequestParam(@Length(max = 3, message = "长度不能大于{max}") @RequestParam("name") String name,
                                          @Max(value = 20, message = "最大值为{value}") @RequestParam("age") Integer age) {
        return ApiResult.success("校验表单参数");
    }
}
