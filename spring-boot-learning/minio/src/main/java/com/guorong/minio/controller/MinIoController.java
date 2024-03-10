package com.guorong.minio.controller;

import com.guorong.common.ApiResult;
import com.guorong.minio.properties.MinIoProperties;
import com.guorong.minio.service.MinIoService;
import com.guorong.minio.vo.UploadVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/minio/")
public class MinIoController {

    private final MinIoService minIoService;

    private final MinIoProperties minIoProps;

    @PostMapping("uploadFile")
    public ApiResult<UploadVo> uploadFile(MultipartFile file) throws IOException {
        UploadVo uploadVo = new UploadVo();
        uploadVo.setOriginalFileName(file.getOriginalFilename());
        String newFileName = minIoService.uploadFile(file.getInputStream(), file.getOriginalFilename());
        if (Objects.nonNull(newFileName)) {
            uploadVo.setNewFileName(newFileName);
            String accessUrl = minIoProps.getEndpoint() + ":" + minIoProps.getPort() + "/" + minIoProps.getBucketName() + "/" + newFileName;
            uploadVo.setAccessUrl(accessUrl);
            return ApiResult.success(uploadVo);
        }
        return ApiResult.fail(null);
    }


    @PostMapping("deleteFile/{objectName}")
    public ApiResult deleteFile(@PathVariable("objectName") String objectName) {
        return minIoService.deleteFile(objectName) ? ApiResult.success() : ApiResult.fail();
    }

    @GetMapping("getBaseUrl")
    public ApiResult getBaseUrl() {
        String accessUrl = minIoProps.getEndpoint() + ":" + minIoProps.getPort() + "/" + minIoProps.getBucketName() + "/";
        return ApiResult.success(accessUrl);
    }

}
