package com.fyj.header;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.fyj.header.mapper")
@SpringBootApplication
public class headerApplication {
    public static void main(String[] args) {

        SpringApplication.run(headerApplication.class, args);

    }
}
