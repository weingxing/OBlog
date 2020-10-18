package com.oxygen.oblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@MapperScan("com.oxygen.oblog.dao")
public class OBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OBlogApplication.class, args);
	}

}
