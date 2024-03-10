package com.guorong.mvc.log_aspect;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author guorong
 * @date 2021-06-05
 */
@RestController
@RequestMapping("/log")
public class SystemLogController {

    @PostMapping("/requestBody/{companyName}")
    @SystemLog(firstModule = "日志", secondModule = "RequestBody", actionType = "新增")
    public String requestBody(@RequestBody JsonNode requestBody, @PathVariable String companyName) {
        return "success";
    }

    @PostMapping("/requestParam")
    @SystemLog(firstModule = "日志", secondModule = "RequestParam", actionType = "新增")
    public String requestParam(@RequestParam("userName") String userName,
                               @RequestParam("age") Integer age) {
        return "success";
    }

    @PostMapping("/pathVariable/{name}")
    @SystemLog(firstModule = "日志", secondModule = "PathVariable", actionType = "新增")
    public String pathVariable(@PathVariable("name") String name) {
        return "success";
    }

    @PostMapping("/requestPart")
    @SystemLog(firstModule = "日志", secondModule = "RequestPart", actionType = "新增")
    public String requestPart(@RequestPart("file") MultipartFile file) {
        return "success";
    }

}
