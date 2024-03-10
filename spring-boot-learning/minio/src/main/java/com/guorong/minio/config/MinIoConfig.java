package com.guorong.minio.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guorong.minio.properties.BucketPolicyProperties;
import com.guorong.minio.properties.MinIoProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MinIoConfig {

    /**
     * @return minio 客户端
     */
    @Bean
    @Autowired
    public MinioClient minioClient(MinIoProperties minIoProp, BucketPolicyProperties policyProp) throws Exception {
        // 创建 minio 客户端
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minIoProp.getEndpoint(), minIoProp.getPort(), false)
                .credentials(minIoProp.getAccessKey(), minIoProp.getSecretKey())
                .build();
        // 判断 bucket 是否存在
        if (!existBucketName(minioClient, minIoProp)) {
            // 创建bucket
            createBucket(minioClient, minIoProp);
            // 设置 bucket 策略
            setPolicy(minioClient, minIoProp, policyProp);
        }
        return minioClient;
    }

    /**
     * 判断 bucket 是否存在
     *
     * @param minIoProp
     * @param minioClient
     * @return
     */
    private boolean existBucketName(MinioClient minioClient, MinIoProperties minIoProp) throws Exception {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(minIoProp.getBucketName())
                .build();
        return minioClient.bucketExists(bucketExistsArgs);
    }


    /**
     * 创建 bucket
     * @param minioClient
     * @param minIoProp
     * @throws Exception
     */
    private void createBucket(MinioClient minioClient, MinIoProperties minIoProp) throws Exception {
        MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                .bucket(minIoProp.getBucketName())
                .build();
        minioClient.makeBucket(makeBucketArgs);
    }


    /**
     * 配置bucket策略
     * @param minioClient
     * @param minIoProp
     * @param policyProp
     * @throws Exception
     */
    private void setPolicy(MinioClient minioClient, MinIoProperties minIoProp, BucketPolicyProperties policyProp) throws Exception {
        // 需要复制属性: ObjectMapper 无法序列化 Spring 管理的实例
        BucketPolicyProperties bucketPolicyProp = new BucketPolicyProperties();
        BeanUtils.copyProperties(policyProp, bucketPolicyProp);
        String bucketPolicyJson = new ObjectMapper().writeValueAsString(bucketPolicyProp);
        // 设置 bucket policy
        SetBucketPolicyArgs policyArgs = SetBucketPolicyArgs.builder()
                .bucket(minIoProp.getBucketName())
                .config(bucketPolicyJson)
                .build();
        minioClient.setBucketPolicy(policyArgs);
    }


}
