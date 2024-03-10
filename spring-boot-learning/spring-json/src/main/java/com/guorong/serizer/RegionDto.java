package com.guorong.serizer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegionDto implements Serializable {
    private String provinceCode;
    private String cityCode;
    private String countyCode;
}
