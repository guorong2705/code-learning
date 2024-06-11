package com.guorong.type._long;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class LongDto implements Serializable {

    private BigDecimal long1;

    private Long long2;
}
