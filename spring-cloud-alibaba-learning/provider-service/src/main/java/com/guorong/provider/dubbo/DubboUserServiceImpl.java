package com.guorong.provider.dubbo;

import com.guorong.provider.dto.UserDto;
import com.guorong.provider.service.DubboUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
@DubboService(version = DubboUserService.VERSION_1_0_0)
public class DubboUserServiceImpl implements DubboUserService {

    private List<UserDto> userDtoList = new ArrayList<UserDto>();

    @PostConstruct
    public void init() {
        userDtoList.add(new UserDto("张三", 31));
        userDtoList.add(new UserDto("李四", 32));
        userDtoList.add(new UserDto("王五", 33));
    }


    public List<UserDto> getAllUser() {
        return userDtoList;
    }
}
