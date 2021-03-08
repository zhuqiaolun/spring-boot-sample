package com.demon.sample;

import com.demon.sample.util.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Demon
 * @date 2021/3/5 14:45
 * @description: 测试
 */
@SpringBootTest
class JarMainApplicationTests {

    @Test
    void contextLoads() {
        String doGet = HttpClientUtil.doGet("https://www.baidu.com/");
        System.out.println(doGet);
    }

}
