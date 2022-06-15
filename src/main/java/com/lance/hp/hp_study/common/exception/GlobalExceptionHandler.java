package com.lance.hp.hp_study.common.exception;

import com.lance.hp.hp_study.common.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description 全局异常处理
 * @createTime 2022/06/15 14:16:00
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult<Object> handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }

        return CommonResult.failed(e.getMessage());
    }

}
