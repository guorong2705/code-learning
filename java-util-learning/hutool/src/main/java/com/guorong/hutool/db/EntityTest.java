package com.guorong.hutool.db;

import cn.hutool.core.annotation.Alias;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 自定义对象转换为 Entity
 */
class EntityTest {

    @Setter
    @Getter
    @Builder
    static class Student {
        private String name;
        private Integer age;
        @Alias("user_name") // 别名注解，使用此注解的字段、方法、参数等会有一个别名，用于Bean拷贝、Bean转Map
        private String userName;
    }

    private DataSource dataSource = DSFactory.get("group_goddog_mysql");

    @Test
    public void test() throws SQLException {
        Entity entity = Entity.create("user");
        Student user = Student.builder()
                .name("张三")
                .age(21)
                .userName("zhangshang")
                .build();
        BeanUtil.copyProperties(user, entity);
        System.out.println(entity);
        Db.use(dataSource).insert(entity);
    }


}
