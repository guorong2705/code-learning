package com.guorong.security.service;

import com.guorong.security.dao.DbUserDao;
import com.guorong.security.entity.DbUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author guorong
 * @date 2020-11-07
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final DbUserDao dbUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 获取用户
        DbUser dbUser = dbUserDao.getUserByUsername(username);
        if (Objects.isNull(dbUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserDetails userDetails = User
                .withUsername(dbUser.getUsername())
                .password(dbUser.getPassword())
                .roles("vip")
                .build();

        return userDetails;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPasswd = "123456";
        String encode = encoder.encode(rawPasswd);
        System.out.println("encode = " + encode);
        System.out.println(encoder.matches(rawPasswd, encode));
    }


}
