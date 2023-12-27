package com.y1jt.spzxmanager.controller;

import com.y1jt.commonutil.utils.AuthContextUtil;
import com.y1jt.spzxmanager.service.SysUserService;
import com.y1jt.spzxmanager.service.ValidateCodeService;
import com.y1jt.spzxmodel.dto.system.LoginDto;
import com.y1jt.spzxmodel.vo.common.Result;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import com.y1jt.spzxmodel.vo.system.LoginVo;
import com.y1jt.spzxmodel.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : YeJT
 * @date : 2023-11-23 16:22
 * @Description: 用户接口
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Operation(summary = "用户登入")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "生成验证码")
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token) {
//        SysUser sysUser = sysUserService.getUserInfo(token);
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "用户退出登入")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
