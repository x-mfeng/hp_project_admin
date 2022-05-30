package com.lance.hp.hp_study.domain.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
@Setter
@Getter
public class SysUserPO implements Serializable {
    /**
     *主键id
     */
    @ApiModelProperty("主键id")
    private Long id;
    /**
     *账号
     */
    @Size(max= 64,message="账号长度不能超过64")
    @ApiModelProperty("")
    @Length(max= 64,message="账号长度不能超过64")
    private String username;
    /**
     *密码
     */
    @Size(max= 64,message="密码长度不能超过64")
    @ApiModelProperty("密码")
    @Length(max= 64,message="密码长度不能超过64")
    private String password;
    /**
     * 头像
     */
    @Size(max= 500,message="头像长度不能超过500")
    @ApiModelProperty("头像")
    @Length(max= 500,message="头像长度不能超过500")
    private String icon;
    /**
     * 邮箱
     */
    @Size(max= 100,message="邮箱长度不能超过100")
    @ApiModelProperty("邮箱")
    @Length(max= 100,message="邮箱长度不能超过100")
    private String email;
    /**
     * 昵称
     */
    @Size(max= 200,message="昵称长度不能超过200")
    @ApiModelProperty("昵称")
    @Length(max= 200,message="昵称长度不能超过200")
    private String nickName;
    /**
     * 备注信息
     */
    @Size(max= 500,message="备注信息长度不能超过500")
    @ApiModelProperty("备注信息")
    @Length(max= 500,message="备注信息长度不能超过500")
    private String note;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    private Date loginTime;
    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private String status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}