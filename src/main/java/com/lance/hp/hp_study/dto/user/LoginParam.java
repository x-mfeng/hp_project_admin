package com.lance.hp.hp_study.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName LoginParam.java
 * @Description 登录参数实体类
 * @createTime 2022/05/19 14:28:00
 */
@Data
public class LoginParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
