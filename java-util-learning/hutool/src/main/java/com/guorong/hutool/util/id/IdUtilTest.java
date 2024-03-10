package com.guorong.hutool.util.id;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

/**
 * 生成ID工具类
 * @author guorong
 * @date 2021-08-07
 */
public class IdUtilTest {

    @Test
    public void testUUID() {
        // 9dfce278f37e48399d38c4380c4eb547
        String uuid = IdUtil.simpleUUID();
        System.out.println(uuid);
        // 0d9eddbf053142149fd43a8b498fc15c
        String fastUUID = IdUtil.fastSimpleUUID();
        System.out.println(fastUUID);
    }


    @Test
    public void testObjectId() {
        // 610dfe021b78545881d4c50a
        String objectId = IdUtil.objectId();
        System.out.println(objectId);
    }

    /**
     * 获取单例的 Twitter 的 Snowflake 算法生成器对象。
     * 分布式系统中，有一些需要使用全局唯一ID的场景，有些时候我们希望能使用一种简单一些的ID，并且希望ID能够按照时间有序生成。
     */
    private static Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    @Test
    public void test() {
        // 1423849960390135808
        long id = snowflake.nextId();
        System.out.println(id);
        // 1423850083656536065
        String idStr = snowflake.nextIdStr();
        System.out.println(idStr);
    }


}
