package com.fyj.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fyj.system.mapper")
public class systemApplication {
    public static void main(String[] args) {
        SpringApplication.run(systemApplication.class);
    }
}
