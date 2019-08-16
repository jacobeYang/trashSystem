package com.jacob.trash;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer  {

//  静态文件访问映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/image_file/**").addResourceLocations("file:E://trashSys/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

//  解决跨域访问问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")          需要学习下具体配置的含义
//                .allowedOrigins("http://127.0.0.1:8080")
//                .allowedMethods("PUT", "DELETE","GET")
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(false).maxAge(3600);
        registry.addMapping("/**") .allowCredentials(false).maxAge(3600);
    }

}
