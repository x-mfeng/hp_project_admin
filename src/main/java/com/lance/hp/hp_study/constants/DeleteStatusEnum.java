package com.lance.hp.hp_study.constants;

import lombok.Getter;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName DeleteStatusEnum.java
 * @Description 删除状态枚举类
 * @createTime 2022/05/25 09:16:00
 */
public enum DeleteStatusEnum {
    /*删除为1*/
    DELETE("1"),
    /*正常状态为0*/
    NORMAL("0"),
    ;
    @Getter
    private final String value;

    DeleteStatusEnum(String value) {
        this.value = value;
    }

}
