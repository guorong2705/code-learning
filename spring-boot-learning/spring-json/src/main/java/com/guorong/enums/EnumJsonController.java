package com.guorong.enums;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enum/")
public class EnumJsonController {

    @GetMapping("listSizeTypeEnum")
    public List<SizeTypeEnum> listSizeTypeEnum() {
        return Arrays.asList(SizeTypeEnum.values());
    }

    @GetMapping("listSeasonEnum")
    public List<SeasonEnum> listSeasonEnum() {
        return Arrays.asList(SeasonEnum.values());
    }

    @GetMapping("listPersonTypeEnum")
    public List<PersonTypeEnum> listPersonTypeEnum() {
        return Arrays.asList(PersonTypeEnum.values());
    }
}
