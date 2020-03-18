package com.personal.website.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(value = "testController",description = "测试管理")
public class TestController {

    @ApiOperation(value = "测试接口")
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
