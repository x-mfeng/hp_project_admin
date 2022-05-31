package com.lance.hp.hp_study.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lance.hp.hp_study.common.exception.Asserts;
import com.lance.hp.hp_study.constants.DeleteStatusEnum;
import com.lance.hp.hp_study.constants.StatusEnum;
import com.lance.hp.hp_study.domain.dept.SysDeptPO;
import com.lance.hp.hp_study.dto.dept.DeptDTO;
import com.lance.hp.hp_study.mapper.SysDeptMapper;
import com.lance.hp.hp_study.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
     *
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
    public Boolean saveDept(SysDeptPO sysDeptPO) {
        //根据parentId查询部门信息
        SysDeptPO parent = this.getById(sysDeptPO.getParentId());

        sysDeptPO.setAncestors(parent.getAncestors() + "," + sysDeptPO.getParentId());
        int count=0;
        //判断是新增还是修改
        if (sysDeptPO.getDeptId() != null) {
            //设置时间
            sysDeptPO.setUpdateTime(LocalDateTime.now());
            //设置userid
            sysDeptPO.setUpdateBy(1L);
            //修改
          return this.update(new UpdateWrapper<SysDeptPO>().lambda()
                    .set(SysDeptPO::getDeptName,sysDeptPO.getDeptName())
                    .set(SysDeptPO::getParentId,sysDeptPO.getParentId())
                    .set(SysDeptPO::getAncestors,sysDeptPO.getAncestors())
                    .set(SysDeptPO::getOrderNum,sysDeptPO.getOrderNum())
                    .set(SysDeptPO::getPhone,sysDeptPO.getPhone())
                    .set(SysDeptPO::getEmail,sysDeptPO.getEmail())
                    .set(SysDeptPO::getLeader,sysDeptPO.getLeader())
                    .set(SysDeptPO::getStatus,sysDeptPO.getStatus())
                    .set(SysDeptPO::getUpdateBy,sysDeptPO.getUpdateBy())
                    .set(SysDeptPO::getUpdateTime,sysDeptPO.getUpdateTime())
                    .eq(SysDeptPO::getDeptId, sysDeptPO.getDeptId()));
        } else {
            //新增
            //判断是否启用
            if (!StatusEnum.ENALBE.getValue().equals(parent.getStatus())) {
                Asserts.fail("部门停用，不允许新增");
            }
            //设置时间
            sysDeptPO.setCreateTime(LocalDateTime.now());
            //设置userid
            sysDeptPO.setCreateBy(1L);
           return this.save(sysDeptPO);
        }
    }


}




