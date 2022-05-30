package com.lance.hp.hp_study.common.api;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName ResultCode.java
 * @Description  枚举了一些常用API操作码
 * @createTime 2022/05/18 16:55:00
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
