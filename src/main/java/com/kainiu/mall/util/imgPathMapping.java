package com.kainiu.mall.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class imgPathMapping  extends WebMvcConfigurerAdapter {
    @Value("${picpath}")
    private String UPLOAD_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:"+UPLOAD_PATH);
    }
}
