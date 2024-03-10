package com.guorong.minio;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guorong.minio.properties.BucketPolicyProperties;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MinioTest {

    @Autowired
    private MinioClient minioClient;

    private String bucketName = "minio-image-02";

    /**
     * 判断 bucket 是否存在
     */
    @Test
    public void testBucketExists() throws Exception{
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        boolean bucketExists = minioClient.bucketExists(bucketExistsArgs);
        log.info("bucket exists: " + bucketExists);
    }

    /**
     * 创建 bucket
     */
    @Test
    public void testMakeBucket() throws Exception{
        MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
        minioClient.makeBucket(makeBucketArgs);
        log.info("make bucket success--->>>");
    }

    /**
     * 设置 bucket 策略
     */
    @Test
    public void testSetBucketPolicy() throws Exception {
        // 创建策略设置的json对象
        BucketPolicyProperties.Statement statement = new BucketPolicyProperties.Statement();

        statement.setAction(Arrays.asList("s3:GetObject"));
        statement.setEffect("Allow");
        statement.setPrincipal("*");
        statement.setResource(Arrays.asList("arn:aws:s3:::*"));

        BucketPolicyProperties bucketPolicyProperties = new BucketPolicyProperties();

        bucketPolicyProperties.setStatements(Arrays.asList(statement));
        bucketPolicyProperties.setVersion("2012-10-17");

        String policyJson = new ObjectMapper().writeValueAsString(bucketPolicyProperties);

        // 设置bucket策略
        SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                .bucket(bucketName)
                .config(policyJson)
                .build();
        minioClient.setBucketPolicy(setBucketPolicyArgs);
    }


    @Test
    public void uploadObject() throws Exception {
        // 上传未知大小的输入流
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("01.jpg");

        String objectName =  IdUtil.simpleUUID() + ".jpg";

        String mimeType = FileUtil.getMimeType(objectName);

        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .contentType(mimeType)
                .stream(inputStream, -1, 10485760)
                .build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
        log.info("upload file success, filename=" + objectName);
    }

    @Test
    public void test () {
        String mimeType = FileUtil.getMimeType("10.mp4");
        System.out.println(mimeType);
    }


}
