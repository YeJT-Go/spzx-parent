package com.y1jt.spzxmanager.service;

import com.github.pagehelper.PageInfo;
import com.y1jt.spzxmodel.dto.system.SysRoleDto;
import com.y1jt.spzxmodel.entity.system.SysRole;

import java.util.Map;

/**
 * @author : YeJT
 * @date : 2023-11-30 10:54
 * @Description: TODO
 */
public interface SysRoleService {
    PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAll();
}
