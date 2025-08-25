package com.tiv.easy.im.auth.exception;

import com.tiv.easy.im.auth.common.CodeEnum;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class GlobalException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public GlobalException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }

    public GlobalException(CodeEnum codeEnum, String message) {
        super(message);
        this.code = codeEnum.getCode();
    }

}
