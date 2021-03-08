package com.demon.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Demon
 * @date 2021/3/5 14:16
 * @description: jar 启动类型
 */
@Slf4j
@SpringBootApplication
public class JarMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(JarMainApplication.class, args);
        log.info("jar 程序已启动···");
    }

}
