package com.lance.hp.hp_study.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lance.hp.hp_study.domain.user.SysUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MaoFengX
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2022-05-18 14:18:49
* @Entity com.lance.hp.hp_study.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPO> {

}




