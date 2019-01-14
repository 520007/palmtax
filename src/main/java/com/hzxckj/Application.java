package com.hzxckj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@MapperScan("com.devloper.joker.*.cascade.mapper")
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements WebMvcConfigurer {

    // tomcat 不打包配置
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new
        // BaseInterceptors()).addPathPatterns("/back/**");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
