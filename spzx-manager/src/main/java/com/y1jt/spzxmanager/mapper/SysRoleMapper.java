package com.y1jt.spzxmanager.mapper;

import com.y1jt.spzxmodel.dto.system.SysRoleDto;
import com.y1jt.spzxmodel.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : YeJT
 * @date : 2023-11-30 10:56
 * @Description: TODO
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    List<SysRole> findAll();
}
