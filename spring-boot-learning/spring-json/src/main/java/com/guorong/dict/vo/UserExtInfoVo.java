package com.guorong.dict.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guorong.dict.Dictionary;
import com.guorong.dict.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserExtInfoVo implements Serializable {
    private Integer id;

    @Dictionary(dictCodeType = StatusType.class)
    private String status;

    public UserExtInfoVo(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
}
