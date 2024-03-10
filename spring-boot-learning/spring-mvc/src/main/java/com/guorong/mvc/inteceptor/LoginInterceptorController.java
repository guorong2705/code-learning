package com.guorong.mvc.inteceptor;

import com.guorong.mvc.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/interceptor/")
public class LoginInterceptorController {

    @IsLogin(isLogin = true)
    @GetMapping("getUserInfo")
    public Person getUserInfo() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(25);
        return person;
    }
}
