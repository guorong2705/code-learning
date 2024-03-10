package com.guorong.mvc.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author 80006000
 */
public final class ResponseUtil {

    private ResponseUtil() {}
    /**
     * 设置相应内容类型
     *
     * @param response
     * @param fileNameSuffix 文件后缀:例如：jpeg,png,xlsx
     */
    public static void setContentType(HttpServletResponse response, String fileNameSuffix) {
        if (Objects.isNull(response) || StringUtils.isEmpty(fileNameSuffix)) {
            throw new UnsupportedOperationException("参数不能为空");
        }
        if ("png".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("image/png;charset=utf-8");
        } else if ("jpg".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("image/jpg;charset=utf-8");
        } else if ("pdf".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/pdf;charset=utf-8");
        } else if ("xlsx".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
        } else if ("xls".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
        } else if ("rar".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/rar;charset=utf-8");
        } else if ("zip".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/zip;charset=utf-8");
        } else if ("doc".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/doc;charset=utf-8");
        } else if ("docx".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/docx;charset=utf-8s");
        } else if ("txt".equalsIgnoreCase(fileNameSuffix)) {
            response.setContentType("application/txt;charset=utf-8");
        } else {
            throw new UnsupportedOperationException("未找到匹配格式");
        }
    }




}
