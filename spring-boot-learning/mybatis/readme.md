### 多个数据源配置: 

`application.yml`

```yaml
dbconfig:
  masterdb: # 数据库01配置
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.56.101:3306/mybatis?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: 123456
  slavedb: # 数据库02配置
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://192.168.56.101:5432/mybatis
    username: postgres
    password: 123456
```

### 通过databaseIdProvider支持多数据库

```xml
<select id="selectAll" resultMap="BaseResultMap" databaseId="mysql">
    select user_id, username, password, nickname, age, create_time, update_time
    from user
</select>
```

通过配置：`databaseId="mysql"`实现不同数据库的切换

### 数据库账号

```shell
# mysql 数据库账号
账号：root 密码：123456
-----------------------------------------------------------------
# postgresql 数据库账号
账号：postgres 密码：123456
```

