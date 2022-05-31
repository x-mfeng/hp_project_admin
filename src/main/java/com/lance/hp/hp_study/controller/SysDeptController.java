package com.lance.hp.hp_study.controller;

import com.lance.hp.hp_study.common.api.CommonResult;
import com.lance.hp.hp_study.domain.dept.SysDeptPO;
import com.lance.hp.hp_study.dto.dept.DeptDTO;
import com.lance.hp.hp_study.service.SysDeptService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName SysDeptController.java
 * @Description 系统部门
 * @createTime 2022/05/24 16:41:00
 */
@Slf4j
@RestController
@RequestMapping("dept")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    public SysDeptController(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    @ApiOperation(value = "查询部门列表")
    @PostMapping("getList")
    @ResponseBody
    public CommonResult<List<SysDeptPO>> getList(@RequestBody DeptDTO deptDTO) {

        return CommonResult.success(this.sysDeptService.getDeptList(deptDTO));
    }

    @ApiOperation(value = "保存部门")
    @PostMapping("saveDept")
    @ResponseBody
    public CommonResult<Object> saveDept(@RequestBody SysDeptPO sysDeptPO) {
        if (this.sysDeptService.saveDept(sysDeptPO)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }


    }
}
