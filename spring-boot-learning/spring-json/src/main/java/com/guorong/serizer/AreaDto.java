package com.guorong.serizer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AreaDto implements Serializable {
    private List<RegionDto> regionDtoList;
}
