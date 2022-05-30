package com.lance.hp.hp_study.service;

import com.lance.hp.hp_study.domain.dept.SysDeptPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lance.hp.hp_study.dto.dept.DeptDTO;

import java.util.List;

/**
* @author MaoFengX
* @description 针对表【sys_dept(部门表)】的数据库操作Service
* @createDate 2022-05-24 16:34:04
*/
public interface SysDeptService extends IService<SysDeptPO> {

    List<SysDeptPO> getDeptList(DeptDTO deptDTO);

    void saveDept(SysDeptPO sysDeptPO);
}
