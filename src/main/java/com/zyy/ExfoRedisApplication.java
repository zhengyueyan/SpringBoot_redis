package com.zyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.zyy.mapper")
public class ExfoRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExfoRedisApplication.class, args);
	}
}
