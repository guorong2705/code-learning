package com.guorong.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guorong
 * @date 2020-04-22
 */
public class Md5Demo {

    /**
     * 将 字符串进行 MD5 加密 并且16进制的字符串
     * @param password
     * @return
     */
    public String md5Password(String password) throws NoSuchAlgorithmException {

        // 指定算法为md5
        String algorithm = "MD5";
        // 创建消化器
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

        // 将明文密码转换为字节数组
        byte[] inputBytes = password.getBytes();

        // 消化明文字节数组，返回加密后的数组
        byte[] outputBytes = messageDigest.digest(inputBytes);

        // 转换为 16 进制的大写字符串
        return new BigInteger(1, outputBytes).toString(16).toUpperCase();
    }



    public static void main(String[] args) throws NoSuchAlgorithmException {
        Md5Demo md5Demo = new Md5Demo();
        String md5Password = md5Demo.md5Password("123456");
        System.out.println(md5Password);
    }



}
