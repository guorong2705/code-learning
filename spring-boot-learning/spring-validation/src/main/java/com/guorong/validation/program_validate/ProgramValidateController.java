package com.guorong.validation.program_validate;

import com.guorong.common.ApiResult;
import com.guorong.validation.enum_validate.enums.EnumValid;
import com.guorong.validation.enum_validate.enums.SizeEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 编程校验
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/program/validate/")
public class ProgramValidateController {

    private final Validator validator;

    @PostMapping("getUser")
    public ApiResult getUser(@RequestBody UserDto userDto) {
        Set<ConstraintViolation<UserDto>> constraintViolationSet = validator.validate(userDto);
        if (constraintViolationSet.isEmpty()) {
            // 校验通过，不存在错误
            return ApiResult.success();
        }
        // 校验不通过
        Map<String,String> dataMap = new HashMap<>();
        for (ConstraintViolation<?> constraintViolation : constraintViolationSet) {
            String fieldName = ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName();
            String message = constraintViolation.getMessage();
            dataMap.put(fieldName, message);
        }
        return ApiResult.fail(dataMap);
    }

    @Data
    public static class UserDto implements Serializable {

        @NotEmpty(message = "用户名不能为空")
        private String userName;

        @NotNull(message = "年龄不能空")
        @Range(min = 1, max = 80, message = "年龄区间[{min},{max}]")
        private Integer age;
    }


}
