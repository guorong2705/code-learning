package com.guorong.dict.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guorong.dict.Dictionary;
import com.guorong.dict.enums.GenderType;
import com.guorong.dict.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class UserVo implements Serializable {

    private Integer id;

    private String userName;

    @Dictionary(dictCodeType = StatusType.class, codeField = "status")
    private String statusName = Dictionary.DEFAULT_DIC_VALUE;

    private String status;

    private String gender;

    @Dictionary(dictCodeType = GenderType.class, codeField = "gender")
    private String genderName = Dictionary.DEFAULT_DIC_VALUE;

    /**
     * 扩展信息
     */
    private List<UserExtInfoVo> userExtInfoVos;

    // --------------日期类 -----------------------------------------------------

    @JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Shanghai")
    private Date jsonFormatDate = new Date();

    private Date date = new Date();

    @JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Shanghai")
    private LocalDate jsonFormatLocalDate = LocalDate.now();

    private LocalDate localDate = LocalDate.now();

    @JsonFormat(pattern = "yyyyMMdd", timezone = "Asia/Shanghai")
    private LocalDateTime jsonFormatLocalDateTime = LocalDateTime.now();

    private LocalDateTime localDateTime = LocalDateTime.now();

    public UserVo() {
    }

    public UserVo(Integer id, String userName, String status, String gender) {
        this.id = id;
        this.userName = userName;
        this.status = status;
        this.gender = gender;
    }

}

