package com.lance.hp.hp_study.common.exception;

import com.lance.hp.hp_study.common.api.IErrorCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName Asserts.java
 * @Description 断言处理类，用于抛出各种API异常
 * @createTime 2022/05/19 15:15:00
 */
@Slf4j
public class Asserts {
    public static void fail(String message) {
        log.warn(message);
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
