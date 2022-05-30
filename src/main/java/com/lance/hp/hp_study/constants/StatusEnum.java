package com.lance.hp.hp_study.constants;

import lombok.Getter;
import lombok.Value;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName StatusEnum.java
 * @Description 状态的枚举类
 * @createTime 2022/05/25 09:07:00
 */
public enum StatusEnum {
    /*启用为1*/
    ENALBE("1"),
    /*禁用为0*/
    UNALBE("0"),
    ;


    @Getter
    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }
}
