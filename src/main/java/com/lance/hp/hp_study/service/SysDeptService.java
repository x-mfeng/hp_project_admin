package com.lance.hp.hp_study.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 根据deptId进行保存或修改
     * @param sysDeptPO 部门信息
     * @return 保存操作结果
     */
   Boolean saveDept(SysDeptPO sysDeptPO);

    /**
     * 删除部门
     * @param deptId 部门id
     * @return 删除操作结果
     */
    Boolean deleteDept(String deptId);
}
