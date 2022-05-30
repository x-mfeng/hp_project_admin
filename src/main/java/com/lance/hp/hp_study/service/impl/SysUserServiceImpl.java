package com.lance.hp.hp_study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lance.hp.hp_study.common.exception.Asserts;
import com.lance.hp.hp_study.domain.user.SysUserPO;
import com.lance.hp.hp_study.dto.user.UserDTO;
import com.lance.hp.hp_study.service.SysUserService;
import com.lance.hp.hp_study.mapper.SysUserMapper;
import com.lance.hp.hp_study.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author MaoFengX
* @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
* @createDate 2022-05-18 14:18:49
*/
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPO>
    implements SysUserService{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     *
     * @param username 账号
     * @param password 密码
     * @return 返回token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        //查询中户是否存在
        final SysUserPO user = this.getOne(
                new QueryWrapper<SysUserPO>().lambda().eq(SysUserPO::getUsername, username));
        try{
            if (Objects.isNull(user)) {
                log.warn("用户:{}不存在!",username);
                Asserts.fail("用户不存在!");
            }
            //校对密码
            if(!user.getPassword().equals(password)){
                Asserts.fail("账户或密码不正确!");
            }
            token= jwtTokenUtil.generateToken(username);
        } catch (Exception e) {
            log.warn("登录异常:{}", e.getMessage());
        }


        return token;
    }

    @Override
    public SysUserPO getInfo(String token) {
        //从token获取用户名
       String username= jwtTokenUtil.getUserNameFromToken(token);
        final SysUserPO user = this.getOne(
                new QueryWrapper<SysUserPO>().lambda().eq(SysUserPO::getUsername, username));
        if (!Objects.isNull(user)) {
          return user;
        }
       return null;
    }

    @Override
    public List<SysUserPO> getList(UserDTO userDTO) {
        PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());

        return this.list();
    }
}




