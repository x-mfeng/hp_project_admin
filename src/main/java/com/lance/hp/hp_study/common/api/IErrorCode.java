package com.lance.hp.hp_study.common.api;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName IErrorCode.java
 * @Description 封装API的错误码
 * @createTime 2022/05/18 16:54:00
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
