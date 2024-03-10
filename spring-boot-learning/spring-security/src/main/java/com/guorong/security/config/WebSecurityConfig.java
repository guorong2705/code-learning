package com.guorong.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * spring-security 配置类
 *
 * @author guorong
 * @date 2020-11-07
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * 定义授权规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 定制请求的授权规则
                .authorizeRequests()
                .antMatchers("/resource/user/**").hasRole("user")
                .antMatchers("/resource/vip/**").hasRole("vip")
                .anyRequest().permitAll()
                .and()
                // 允许表单登录
                .formLogin()
                // 登录操作提交的用户参数名
                .usernameParameter("username")
                // 登录操作提交的密码参数名
                .passwordParameter("password")
                // 登录操作处理的url
                .loginProcessingUrl("/login")
                // 登录成功转发的url
                .successForwardUrl("/login-success")
                // 登录失败转发的url
                .failureForwardUrl("/login-fail")
                .and()
                .logout()
                // 设置登出操作的url
                .logoutUrl("/logout")
                .and()
                // 禁用跨域伪造
                .csrf().disable();
    }

}
