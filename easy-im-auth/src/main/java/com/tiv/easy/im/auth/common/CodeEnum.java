package com.tiv.easy.im.auth.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码枚举
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    SUCCESS(200, "OK"),
    REGISTER_ERROR(40001, "注册失败,用户已存在"),
    CODE_ERROR(40002, "验证码错误"),
    LOGIN_ERROR(40003, "登录失败,用户名或密码错误"),
    NO_USER_ERROR(40004, "用户不存在"),

    SYSTEM_ERROR(50000, "系统内部异常"),
    SAVE_USER_ERROR(50001, "数据库异常,保存用户失败"),
    SEND_CODE_ERROR(50002, "发送验证码失败"),
    UPDATE_AVATAR_ERROR(50003, "更新头像失败");

    private final int code;

    private final String msg;

}
