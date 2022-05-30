package com.lance.hp.hp_study.common.exception;

import com.lance.hp.hp_study.common.api.IErrorCode;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName ApiException.java
 * @Description 自定义API异常
 * @createTime 2022/05/19 15:11:00
 */
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
