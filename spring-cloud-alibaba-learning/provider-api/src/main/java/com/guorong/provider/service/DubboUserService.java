package com.guorong.provider.service;

import com.guorong.provider.dto.UserDto;

import java.util.List;

public interface DubboUserService {

    String VERSION_1_0_0 = "1.0.0";

    List<UserDto> getAllUser();
}
