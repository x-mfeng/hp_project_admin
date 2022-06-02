package com.lance.hp.hp_study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lance.hp.hp_study.common.api.CommonResult;
import com.lance.hp.hp_study.domain.dept.SysDeptPO;
import com.lance.hp.hp_study.dto.dept.DeptDTO;

import java.util.List;

/**
* @author MaoFengX
* @description 针对表【sys_dept(部门表)】的数据库操作Service
* @createDate 2022-05-24 16:34:04
*/
public interface SysDeptService extends IService<SysDeptPO> {
    /**
     * 部门查询列表
     * @param deptDTO 查询部门dto
     * @return 返回部门列表
     */
    List<SysDeptPO> getDeptList(DeptDTO deptDTO);

    /**
     * 根据dept
     * @param sysDeptPO
     * @return
     */
    CommonResult<Object> saveDept(SysDeptPO sysDeptPO);
}
