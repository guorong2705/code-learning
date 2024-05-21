package com.guorong.dict.vo;

import com.guorong.dict.Dictionary;
import com.guorong.dict.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserExtInfoVo implements Serializable {

    private Integer id;

    private String status;

    @Dictionary(dictCodeType = StatusType.class, codeField = "status")
    private String statusName= Dictionary.DEFAULT_DIC_VALUE;

    public UserExtInfoVo(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
}
