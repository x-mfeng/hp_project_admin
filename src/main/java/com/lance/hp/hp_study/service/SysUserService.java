package com.lance.hp.hp_study.service;

import com.lance.hp.hp_study.domain.user.SysUserPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lance.hp.hp_study.dto.user.UserDTO;

import java.util.List;

/**
 * @author MaoFengX
 * @description 针对表【sys_user(用户信息表)】的数据库操作Service
 * @createDate 2022-05-18 14:18:49
 */
public interface SysUserService extends IService<SysUserPO> {
    /**
     * 登录接口 生成token
     *
     * @param username 账号
     * @param password 密码
     * @return 生成贴可能
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     *
     * @param token token信息
     * @return 用户信息
     */
    SysUserPO getInfo(String token);

    /**
     *
     * @param userDTO 用户查询类
     * @return 返回用户列表
     */
    List<SysUserPO> getList(UserDTO userDTO);

}
