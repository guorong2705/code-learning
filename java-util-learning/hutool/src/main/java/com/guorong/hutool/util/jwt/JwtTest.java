package com.guorong.hutool.util.jwt;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author guorong
 * @date 2021-10-03
 */
public class JwtTest {

    private static final String PAYLOAD_KEY_SUB = "sub";

    private static final String PAYLOAD_KEY_NAME = "name";

    private static final String SECRETE_VALUE = "0123456789";

    @Test
    public void testGenerateToken() {
        // 加密
        HashMap<String, Object> payloadMap = new HashMap<>();
        payloadMap.put(PAYLOAD_KEY_SUB, "123456");
        payloadMap.put(PAYLOAD_KEY_NAME, "guorong");
        String token = JWT.create()
                .addPayloads(payloadMap)
                // 设置密钥
                .setKey(SECRETE_VALUE.getBytes())
                .sign();
        System.out.println(token);
    }


    @Test
    public void  testGet() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTYiLCJuYW1lIjoiZ3Vvcm9uZyJ9.0WPzcgn--vtsJg2GQSbl94qM_bxdQH3rG1eIg_m8s6o";
        JWT jwt = JWT.of(token);
        String algorithm = jwt.getAlgorithm();
        System.out.println("algorithm = " + algorithm);
        Object subValue = jwt.getPayload(PAYLOAD_KEY_SUB);
        System.out.println("sub = " + subValue);
        Object nameValue = jwt.getPayload(PAYLOAD_KEY_NAME);
        System.out.println("name = " + nameValue);

    }

    @Test
    public void test02() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTYiLCJuYW1lIjoiZ3Vvcm9uZyJ9.0WPzcgn--vtsJg2GQSbl94qM_bxdQH3rG1eIg_m8s6o";
        boolean verify = JWT.of(token).setKey(SECRETE_VALUE.getBytes()).verify();
        System.out.println("verify = " + verify);
    }


}
