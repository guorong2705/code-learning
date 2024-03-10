package com.guorong.hutool.util.qr_code;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.BarcodeFormat;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 *
 * @author guorong
 * @date 2021-08-07
 */
public class QrCodeUtilTest {


    /**
     * 生成二维码
     */
    @Test
    public void testQrCode() {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        String targetPath = getClass().getClassLoader().getResource("").getPath().concat("二维码.jpg");
        System.out.println(targetPath);
        File targetFile = FileUtil.file(targetPath);
        String content = "https://hutool.cn/";

        QrConfig qrConfig = QrConfig.create()
                .setWidth(300)
                .setHeight(300);
        QrCodeUtil.generate(content, qrConfig, targetFile);
    }


    /**
     * 生成条形码
     */
    @Test
    public void test() {
        String targetPath = getClass().getClassLoader().getResource("").getPath().concat("条形码.jpg");
        System.out.println(targetPath);
        File targetFile = FileUtil.file(targetPath);
        String content = "123456789";
        QrConfig qrConfig = QrConfig.create()
                .setWidth(250)
                .setHeight(100);
        BufferedImage bufferedImage = QrCodeUtil.generate(content, BarcodeFormat.CODE_128, qrConfig);
        ImgUtil.write(bufferedImage, targetFile);
    }



}
