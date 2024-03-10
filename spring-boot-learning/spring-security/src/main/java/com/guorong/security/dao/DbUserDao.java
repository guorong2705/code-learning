package com.guorong.security.dao;

import com.guorong.security.entity.DbUser;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author guorong
 * @date 2020-11-09
 */
@Repository
public class DbUserDao {

    // 密码加密常量
    private static final String ENCODE_PASSWORD = BCrypt.hashpw("123456", BCrypt.gensalt());
    
    private static List<DbUser> store = new ArrayList<>();
    static {
        store.add(new DbUser("guorong", ENCODE_PASSWORD, "国荣", "18695770526"));
        store.add(new DbUser("mike", ENCODE_PASSWORD, "麦克", "18695770526"));
        store.add(new DbUser("jack", ENCODE_PASSWORD, "杰克", "18695770526"));
    }

    public DbUser getUserByUsername(String username) {
        Optional<DbUser> optional = store.stream()
                .filter(dbUser -> Objects.equals(username, dbUser.getUsername()))
                .findFirst();
        return optional.orElse(null);
    }


}
