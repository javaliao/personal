package com.personal.website.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:Liaozhiwei
 * @Date: 2019/11/28 16:31
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //如果打算将静态文件放在resources下面，则需要启用下面一段代码，如果将静态文件放在webapp下面，则什么也不用配置，直接可以访问
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
