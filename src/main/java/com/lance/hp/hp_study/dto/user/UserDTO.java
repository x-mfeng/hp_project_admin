package com.lance.hp.hp_study.dto.user;

import com.lance.hp.hp_study.common.api.BaseSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName UserDTO.java
 * @Description TODO
 * @createTime 2022/05/23 15:30:00
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseSearch {
    @ApiModelProperty("帐号")
    private String userName;
    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private String status;

}
