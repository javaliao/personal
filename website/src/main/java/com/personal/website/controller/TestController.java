package com.personal.website.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:Liaozhiwei
 * @Date: 2020/3/17 19:07
 */
@Controller
@RequestMapping("/")
@Api(value = "testController",description = "测试管理")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
