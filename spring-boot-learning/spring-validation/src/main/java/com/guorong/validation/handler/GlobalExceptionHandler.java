package com.guorong.validation.handler;

import com.guorong.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * (@RequestBody) 注解的对象的属性校验错误处理, 对象需要使用 @Validated
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, String> dataMap = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ApiResult.fail(dataMap);
    }


    /**
     * (@RequestParam | @PathVariable) 参数校验必须在 Controller 类上标注 @Validated 注解，
     * 并在入参上声明约束注解(如@Min等)。如果校验失败会抛出 ConstraintViolationException 异常。
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult constraintViolationException(ConstraintViolationException ex) {
        Map<String, String> dataMap = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolationList = ex.getConstraintViolations();
        for (ConstraintViolation<? extends Object> constraintViolation : constraintViolationList) {
            String fieldName = ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName();
            String message = constraintViolation.getMessage();
            dataMap.put(fieldName, message);
        }
        return ApiResult.fail(dataMap);
    }

}
