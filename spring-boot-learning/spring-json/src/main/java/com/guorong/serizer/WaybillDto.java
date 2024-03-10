package com.guorong.serizer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WaybillDto implements Serializable {

    private List<RegionDto> regionDtoList;

    private RegionDto regionDto;

    private String name;

    private AreaDto areaDto;

}
