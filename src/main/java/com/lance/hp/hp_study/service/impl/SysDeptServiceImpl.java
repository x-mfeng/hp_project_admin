package com.lance.hp.hp_study.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author MaoFengX
 * @description 针对表【sys_dept(部门表)】的数据库操作Service实现
 * @createDate 2022-05-24 16:34:04
 */
@Slf4j
@Service
@Transactional
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

    /**
     * 有deptId,进行修改.无deptId,进行新增
     *
     * @param sysDeptPO 部门信息
     * @return 保存部门操作结果
     */
    @Override
    public Boolean saveDept(SysDeptPO sysDeptPO) {
        //parentId
        //根据parentId查询部门信息
        SysDeptPO parent = this.getById(sysDeptPO.getParentId());
        //判断parentId是否为0,是不是最顶级节点
        if (Objects.isNull(parent)) {
            sysDeptPO.setAncestors(String.valueOf(sysDeptPO.getParentId()));
        } else {
            sysDeptPO.setAncestors(parent.getAncestors() + "," + sysDeptPO.getParentId());
        }
        boolean boo;
        //判断是新增还是修改
        if (sysDeptPO.getDeptId() != null) {
            //设置时间
            sysDeptPO.setUpdateTime(LocalDateTime.now());
            //设置userid
            sysDeptPO.setUpdateBy(1L);
            //修改
            boo = this.update(new UpdateWrapper<SysDeptPO>().lambda()
                    .set(SysDeptPO::getDeptName, sysDeptPO.getDeptName())
                    .set(SysDeptPO::getParentId, sysDeptPO.getParentId())
                    .set(SysDeptPO::getAncestors, sysDeptPO.getAncestors())
                    .set(SysDeptPO::getOrderNum, sysDeptPO.getOrderNum())
                    .set(SysDeptPO::getPhone, sysDeptPO.getPhone())
                    .set(SysDeptPO::getEmail, sysDeptPO.getEmail())
                    .set(SysDeptPO::getLeader, sysDeptPO.getLeader())
                    .set(SysDeptPO::getStatus, sysDeptPO.getStatus())
                    .set(SysDeptPO::getUpdateBy, sysDeptPO.getUpdateBy())
                    .set(SysDeptPO::getUpdateTime, sysDeptPO.getUpdateTime())
                    .eq(SysDeptPO::getDeptId, sysDeptPO.getDeptId()));
            //查询状态是否为启用
            //启用将所有上级改为启用
            if (StatusEnum.ENALBE.getValue().equals(sysDeptPO.getStatus())) {
                String[] idarr = sysDeptPO.getAncestors().split(",");
                List<Long> ids = Arrays.stream(idarr).map(Long::parseLong).collect(Collectors.toList());
                this.update(new UpdateWrapper<SysDeptPO>().lambda()
                        .set(SysDeptPO::getStatus, sysDeptPO.getStatus())
                        .in(SysDeptPO::getDeptId, ids)
                );
            } else {
                //禁用将所有下级改为禁用
                this.update(new UpdateWrapper<SysDeptPO>().lambda()
                        .eq(SysDeptPO::getParentId, sysDeptPO.getDeptId())
                        .or().like(SysDeptPO::getAncestors, "," + sysDeptPO.getDeptId() + ",")
                        .set(SysDeptPO::getStatus, sysDeptPO.getStatus())
                );
            }

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
            boo = this.save(sysDeptPO);
            //
        }

        return boo;
    }

    /**
     * 根据部门id进行删除
     *
     * @param deptId 部门id
     * @return 删除操作结果
     */
    @Override
    public Boolean deleteDept(String deptId) {
        //判断是否存在下级部门
        List<SysDeptPO> childrenList = this.list(new QueryWrapper<SysDeptPO>().lambda().eq(SysDeptPO::getParentId, deptId));
        if (CollectionUtils.isNotEmpty(childrenList)) {
            Asserts.fail("存在下级部门，不允许删除");
        }
        return this.remove(new QueryWrapper<SysDeptPO>().lambda().eq(SysDeptPO::getDeptId, deptId));
    }

}




