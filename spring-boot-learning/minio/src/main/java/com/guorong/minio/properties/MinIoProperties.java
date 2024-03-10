package com.guorong.minio.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "minio")
@SpringBootConfiguration
public class MinIoProperties implements Serializable {

    /**
     * minio服务器地址
     */
    private String endpoint;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 租户账号
     */
    private String accessKey;

    /**
     * 住户密钥
     */
    private String secretKey;


    private String bucketName;


}
