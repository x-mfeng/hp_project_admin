package com.lance.hp.hp_study.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lance.hp.hp_study.constants.DeleteStatusEnum;
import com.lance.hp.hp_study.domain.dept.SysDeptPO;
import com.lance.hp.hp_study.dto.dept.DeptDTO;
import com.lance.hp.hp_study.mapper.SysDeptMapper;
import com.lance.hp.hp_study.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaoFengX
 * @description 针对表【sys_dept(部门表)】的数据库操作Service实现
 * @createDate 2022-05-24 16:34:04
 */
@Slf4j
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptPO>
        implements SysDeptService {
    /**
     * 查询部门列表
     * @param deptDTO 查询的实体类
     * @return 返回查询部门列表
     */
    @Override
    public List<SysDeptPO> getDeptList(DeptDTO deptDTO) {
        LambdaQueryWrapper<SysDeptPO> queryWrapper =
                new QueryWrapper<SysDeptPO>().lambda().eq(SysDeptPO::getDelFlag, DeleteStatusEnum.NORMAL.getValue());
        if (StrUtil.isNotBlank(deptDTO.getDeptName())) {
            queryWrapper.like(SysDeptPO::getDeptName, deptDTO.getDeptName());
        }
        if (StrUtil.isNotBlank(deptDTO.getStatus())) {
            queryWrapper.eq(SysDeptPO::getStatus, deptDTO.getStatus());
        }
        if (deptDTO.getDeptId() != null) {
            queryWrapper.eq(SysDeptPO::getDeptId, deptDTO.getDeptId());
        }
        if (deptDTO.getParentId() != null) {
            queryWrapper.eq(SysDeptPO::getParentId, deptDTO.getParentId());
        }
        queryWrapper.orderByAsc(SysDeptPO::getOrderNum);
        return this.list(queryWrapper);
    }

    @Override
    public void saveDept(SysDeptPO sysDeptPO) {
        //判断是新增还是修改
        if(sysDeptPO.getDeptId() != null){
            //修改
        }else{
            //新增

        }
    }


}




