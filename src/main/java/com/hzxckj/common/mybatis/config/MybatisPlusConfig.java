package com.hzxckj.common.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        interceptor.setProperties(properties);
        return interceptor;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
