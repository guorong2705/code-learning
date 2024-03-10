package com.guorong.validation.controller;

import com.guorong.common.ApiResult;
import com.guorong.validation.dto.CustomerDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/annotation/validate/")
public class AnnotationValidateController {

    @PostMapping(value = "validateEnum")
    public ApiResult validateEnum(@RequestBody @Validated CustomerDto customerDto) {
        return ApiResult.success();
    }

}
