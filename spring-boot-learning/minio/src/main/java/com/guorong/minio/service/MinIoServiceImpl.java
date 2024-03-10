package com.guorong.minio.service;

import com.guorong.minio.properties.MinIoProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
public class MinIoServiceImpl implements MinIoService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinIoProperties minIoProp;

    @Override
    public String uploadFile(InputStream inputStream, String fileName) {
        try {
            String objectName = String.format("%s/%s%s", LocalDate.now(), UUID.randomUUID(), fileName);
            // 构建minio操作参数对象
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minIoProp.getBucketName())
                    .object(objectName)
                    .stream(inputStream, -1, 10485760)
                    .build();
            // 上传对象
            minioClient.putObject(putObjectArgs);
            return objectName;
        } catch (Exception e) {
            log.error("put object to minio error, fileName={}", fileName, e);
        }
        return null;
    }



    @Override
    public boolean deleteFile(String ObjectName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(minIoProp.getBucketName())
                    .object(ObjectName)
                    .build();
            // 删除对象
            minioClient.removeObject(removeObjectArgs);
            return true;
        } catch (Exception e) {
            log.error("delete object error, objectName={}", ObjectName, e);
        }
        return false;
    }
}
