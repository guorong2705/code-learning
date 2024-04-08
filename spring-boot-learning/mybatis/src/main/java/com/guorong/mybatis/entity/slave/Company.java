package com.guorong.mybatis.entity.slave;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Company implements Serializable {
    private Integer companyId;
    private String companyName;
    private String address;
}