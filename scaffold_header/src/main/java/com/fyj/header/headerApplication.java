package com.fyj.header;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fyj.common.utils.IdWorkers;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.fyj.header.mapper")
@SpringBootApplication
public class headerApplication {
    public static void main(String[] args) {

        SpringApplication.run(headerApplication.class, args);

    }
    @Bean
    public IdWorkers idWorkers() {
        return new IdWorkers();
    }
}
