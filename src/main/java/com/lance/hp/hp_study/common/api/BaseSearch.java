package com.lance.hp.hp_study.common.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName BaseSearch.java
 * @Description 查询父类
 * @createTime 2022/05/19 10:14:00
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    /*页码 */
    @ApiModelProperty("页码")
    private Integer pageNum;
    /*每页数量*/
    @ApiModelProperty("每页数量")
    private Integer pageSize;
}
