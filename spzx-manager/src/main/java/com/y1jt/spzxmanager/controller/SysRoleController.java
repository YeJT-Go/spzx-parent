package com.y1jt.spzxmanager.controller;

import com.github.pagehelper.PageInfo;
import com.y1jt.spzxmanager.service.SysRoleService;
import com.y1jt.spzxmodel.dto.system.SysRoleDto;
import com.y1jt.spzxmodel.entity.system.SysRole;
import com.y1jt.spzxmodel.vo.common.Result;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : YeJT
 * @date : 2023-11-30 10:57
 * @Description: 角色管理接口
 */
@Tag(name = "角色管理接口")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "获取角色列表")
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto) {

        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(current, limit, sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "保存角色")
    @PostMapping("saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新角色信息")
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除角色信息")
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long roleId){
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "查询所有角色信息")
    @GetMapping("/findAllRoles")
    public Result findAllRoles(){
        Map<String,Object> map= sysRoleService.findAll();
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }


}
