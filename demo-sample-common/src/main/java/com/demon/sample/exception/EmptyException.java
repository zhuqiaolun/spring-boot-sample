package com.demon.sample.exception;

/**
 * @author: Demon
 * @date 2021/3/5 14:44
 * @description: 空字符串异常
 */
public class EmptyException extends RuntimeException {

    private String code;

    public EmptyException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
