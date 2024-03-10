package com.guorong.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Company implements Serializable {

    private Integer companyId;

    private String companyName;

    private String address;

    public Company() {
    }

    public Company(Integer companyId, String companyName, String address) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.address = address;
    }
}