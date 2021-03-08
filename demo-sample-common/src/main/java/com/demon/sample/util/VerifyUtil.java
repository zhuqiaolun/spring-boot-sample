package com.demon.sample.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @author: Demon
 * @date 2021/3/5 14:03
 * @description: 验证数值 工具
 */
public class VerifyUtil {

    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    public static boolean isBigDecimal(String string) {
        if (StringUtils.isBlank(string)) {
            return false;
        }
        try {
            new BigDecimal(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isNumeric(String string) {
        if (StringUtils.isBlank(string)) {
            return false;
        } else {
            return NUMBER_PATTERN.matcher(string).matches();
        }
    }
}
