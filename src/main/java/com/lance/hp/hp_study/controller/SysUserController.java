package com.lance.hp.hp_study.controller;

import com.lance.hp.hp_study.common.api.CommonPage;
import com.lance.hp.hp_study.common.api.CommonResult;
import com.lance.hp.hp_study.domain.user.SysUserPO;
import com.lance.hp.hp_study.dto.user.LoginParam;
import com.lance.hp.hp_study.dto.user.UserDTO;
import com.lance.hp.hp_study.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName SysUserController.java
 * @Description 系统人员
 * @createTime 2022/05/18 15:02:00
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @ApiOperation(value = "登录的接口")
    @PostMapping("login")
    @ResponseBody
    public CommonResult<Object> login(@Validated @RequestBody LoginParam loginParam) {
        String token = this.sysUserService.login(loginParam.getUsername(), loginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        log.info(token);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("getInfo")
    @ResponseBody
    public CommonResult<Object> getInfo(@RequestParam(value = "token") String token) {
        SysUserPO sysUserPO = this.sysUserService.getInfo(token.substring(tokenHead.length()));
        return CommonResult.success(sysUserPO);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "获取用户列表")
    @PostMapping("getList")
    @ResponseBody
    public CommonResult<CommonPage<SysUserPO>> getList(@RequestBody UserDTO userDTO) {
        List<SysUserPO> userList = this.sysUserService.getList(userDTO);
        return CommonResult.success(CommonPage.restPage(userList));

    }

}
