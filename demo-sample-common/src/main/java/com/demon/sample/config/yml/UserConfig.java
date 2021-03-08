package com.demon.sample.config.yml;

import com.demon.sample.support.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Demon
 * @date 2021/3/8 13:45
 * @description: 用户配置 (采取自定义yml模式)
 */
@Data
@Component
@PropertySource(value = {"classpath:user.yml"}, factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "demon")
public class UserConfig {

    private Map<String,String> user;

}
