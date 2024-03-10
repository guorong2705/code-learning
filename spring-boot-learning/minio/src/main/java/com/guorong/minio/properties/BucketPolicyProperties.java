package com.guorong.minio.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;

/**
 * bucket 策略配置对象
 */
@Data
@ConfigurationProperties(prefix = "minio.bucket.policy")
@SpringBootConfiguration
public class BucketPolicyProperties implements Serializable {

    @JsonProperty("Statement")
    private List<Statement> statements;

    /**
     * 版本号: 默认固定的值：改变设置bucket策略会报错
     */
    @JsonProperty("Version")
    private String version;

    @Data
    public static class Statement implements Serializable{
        @JsonProperty("Action")
        private List<String> action;
        @JsonProperty("Effect")
        private String effect;
        @JsonProperty("Principal")
        private String principal;
        @JsonProperty("Resource")
        private List<String> resource;
    }

}
