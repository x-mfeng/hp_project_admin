package com.lance.hp.hp_study.dto.dept;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName DeptDTO.java
 * @Description TODO
 * @createTime 2022/05/25 09:06:00
 */
@Data
public class DeptDTO {
    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private String status;
    @ApiModelProperty("部门名称")
    private String deptName;
    @ApiModelProperty("部门ID")
    private Long deptId;
    @ApiModelProperty("父部门ID")
    private Long parentId;
}
