package com.demon.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author: Demon
 * @date 2021/3/5 14:16
 * @description: war 启动类型
 */
@Slf4j
@SpringBootApplication
public class WarMainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WarMainApplication.class, args);
        log.info("war 程序已启动···");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WarMainApplication.class);
    }
}
