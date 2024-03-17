package com.guorong.validation.enum_validate;

import com.guorong.common.ApiResult;
import com.guorong.validation.enum_validate.enums.EnumValid;
import com.guorong.validation.enum_validate.enums.SizeEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/annotation/validate/")
public class AnnotationValidateController {

    @PostMapping(value = "validateEnum")
    public ApiResult validateEnum(@RequestBody @Valid CustomerDto customerDto) {
        return ApiResult.success();
    }

    @Data
    public static class CustomerDto {
        @NotEmpty(message = "不能为空")
        @EnumValid(enumClass = SizeEnum.class)
        private String clothSize;
    }

}
