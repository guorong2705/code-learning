package com.guorong.mvc.upload_download;

import cn.hutool.core.io.FileUtil;
import com.guorong.common.CommonResult;
import com.guorong.mvc.util.RequestUtils;
import com.guorong.mvc.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 上传下载
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/uploadDownload")
public class UploadDownloadController {


    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public CommonResult<String> upload(@RequestPart MultipartFile file) throws IOException {
        // 获取类路径
        ClassPathResource classPathResource = new ClassPathResource("");
        // 在类路径下创建一个指定目录，比如"uploads"
        Path uploadPath = classPathResource.getFile().toPath().resolve("uploads");
        // 如果目录不存在，则创建它
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        // 将上传的文件保存到指定目录
        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return CommonResult.success();
    }

    /**
     * 下载文件
     * @param fileName
     * @throws IOException
     */
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName) throws IOException {
        HttpServletResponse response = RequestUtils.getHttpServletResponse();
        ResponseUtil.setContentType(response, FileUtil.getSuffix(fileName));
        ClassPathResource classPathResource = new ClassPathResource(String.format("/uploads/%s", fileName));
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        InputStream inputStream = classPathResource.getInputStream();
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
