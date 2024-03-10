package com.guorong.serizer;

import com.guorong.common.CommonResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("region")
public class RegionController {

    @PostMapping("addWaybill")
    public CommonResult addWaybill(@RequestBody WaybillDto waybillDto) {
        System.out.println(waybillDto);
        return CommonResult.success();
    }

    @GetMapping("getWaybill")
    public CommonResult getWaybill() {
        WaybillDto waybillDto = new WaybillDto();
        // regionDto
        RegionDto regionDto = new RegionDto();
        regionDto.setProvinceCode("11001");
        regionDto.setCityCode("11002");
        waybillDto.setRegionDto(regionDto);
        // regionDtoList
        List<RegionDto> regionDtoList = Arrays.asList(
                new RegionDto("21001", "21002", "21003"),
                new RegionDto("22001", "22002", "23003"),
                new RegionDto("23001", "23002", "23003")
        );
        waybillDto.setRegionDtoList(regionDtoList);
        // name
        waybillDto.setName("张三");
        // areaDto
        AreaDto areaDto = new AreaDto();
        List<RegionDto> areaRegionDtoList = Arrays.asList(
                new RegionDto("31001", "31002", "31003"),
                new RegionDto("32001", "32002", "33003"),
                new RegionDto("33001", "33002", "33003")
        );
        areaDto.setRegionDtoList(areaRegionDtoList);
        waybillDto.setAreaDto(areaDto);
        return CommonResult.success(waybillDto);
    }
}
