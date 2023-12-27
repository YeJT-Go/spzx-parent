package com.y1jt.spzxmanager.enums;

/**
 * @author : YeJT
 * @date : 2023-11-29 15:23
 * @Description: 缓存key工具类
 */
public enum RedisKeyEnum {

    /**
     * 登入缓存key
     */
    USER_LOGIN("user:login","用户登入"),

    /**
     * 验证码
     */
    USER_VALIDATE("user:validate","验证码");

    private String key;

    private String msg;

    RedisKeyEnum(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public String getMsg() {
        return msg;
    }
}
