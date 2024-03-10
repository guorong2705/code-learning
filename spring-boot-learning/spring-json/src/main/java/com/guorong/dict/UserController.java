package com.guorong.dict;

import com.guorong.common.CommonResult;
import com.guorong.dict.vo.UserExtInfoVo;
import com.guorong.dict.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {

    @PostMapping("getUser")
    public CommonResult<UserVo> getUser() {
        UserVo userVo = new UserVo(1,"张三","1", "0");
        userVo.setUserExtInfoVos(Arrays.asList(new UserExtInfoVo(11, "0")));
        return CommonResult.success(userVo);
    }
}
