package com.y1jt.spzxmanager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.y1jt.spzxmanager.mapper.SysRoleMapper;
import com.y1jt.spzxmanager.service.SysRoleService;
import com.y1jt.spzxmodel.dto.system.SysRoleDto;
import com.y1jt.spzxmodel.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author : YeJT
 * @date : 2023-11-30 10:54
 * @Description: TODO
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto) {
        PageHelper.startPage(current, limit);
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        return new PageInfo<>(sysRoleList);
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public Map<String, Object> findAll() {
        //查询角色表中所有的角色
        List<SysRole> roleList = sysRoleMapper.findAll();

        //查询出已经分配的用户角色

        Map<String, Object> map = new HashMap();
        map.put("allRolesList", roleList);
        return map;
    }
}
