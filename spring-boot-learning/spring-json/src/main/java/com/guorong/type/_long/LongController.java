package com.guorong.type._long;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("type/long/")
public class LongController {

    private final ObjectMapper objectMapper;

    @PostMapping("testLong")
    public String testLong(@Validated @RequestBody LongDto longDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(longDto);
    }

}
