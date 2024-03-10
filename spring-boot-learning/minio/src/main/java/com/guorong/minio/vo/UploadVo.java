package com.guorong.minio.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadVo implements Serializable {

    /**
     * 原来文件名称
     */
    private String originalFileName;

    /**
     * 新文件名称
     */
    private String newFileName;

    /**
     * 访问路径
     */
    private String accessUrl;
}
