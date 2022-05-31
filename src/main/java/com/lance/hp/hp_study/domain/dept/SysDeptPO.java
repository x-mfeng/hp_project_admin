package com.lance.hp.hp_study.domain.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门表
 * @TableName sys_dept
 */
@TableName(value ="sys_dept")
@Data
public class SysDeptPO implements Serializable {
    /**
     * 部门id
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message="[部门id]不能为空")
    @ApiModelProperty("部门id")
    private Long deptId;
    /**
     * 父部门id
     */
    @ApiModelProperty("父部门id")
    private Long parentId;
    /**
     * 祖级列表
     */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("祖级列表")
    @Length(max= 50,message="编码长度不能超过50")
    private String ancestors;
    /**
     * 部门名称
     */
    @Size(max= 30,message="编码长度不能超过30")
    @ApiModelProperty("部门名称")
    @Length(max= 30,message="编码长度不能超过30")
    private String deptName;
    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    /**
     * 负责人
     */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("负责人")
    @Length(max= 20,message="编码长度不能超过20")
    private String leader;
    /**
     * 联系电话
     */
    @Size(max= 11,message="编码长度不能超过11")
    @ApiModelProperty("联系电话")
    @Length(max= 11,message="编码长度不能超过11")
    private String phone;
    /**
     * 邮箱
     */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("邮箱")
    @Length(max= 50,message="编码长度不能超过50")
    private String email;
    /**
     * 部门状态（1正常 0停用）
     */
    @ApiModelProperty("部门状态（1正常 0停用）")
    private String status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    private String delFlag;
    /**
     * 创建者
     */
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("创建者")
    @Length(max= 64,message="编码长度不能超过64")
    private Long createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("更新者")
    @Length(max= 64,message="编码长度不能超过64")
    private Long updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}