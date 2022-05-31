package com.lance.hp.hp_study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lance.hp.hp_study.domain.dept.SysDeptPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author MaoFengX
* @description 针对表【sys_dept(部门表)】的数据库操作Mapper
* @createDate 2022-05-24 16:34:04
* @Entity com.lance.hp.hp_study.domain.dept.SysDept
*/
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptPO> {

}




