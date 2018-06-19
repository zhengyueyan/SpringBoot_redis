package com.zyy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: 阿里数据池配置类
 * @Author: 郑玥延
 * @Date: 13:09 2018/6/19
 */
@Configuration
public class DruidDataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
}
