package com.guorong.minio.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 对象存储 MinIo服务
 */
@Service
public interface MinIoService {

    /**
     * 上传文件
     * @param inputStream 文件的输入流
     * @param fileName 文件的名称
     * @return
     */
    String uploadFile(InputStream inputStream, String fileName);

    boolean deleteFile(String ObjectName);

}
