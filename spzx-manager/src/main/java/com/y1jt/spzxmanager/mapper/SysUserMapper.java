package com.y1jt.spzxmanager.mapper;

import com.y1jt.spzxmodel.dto.system.SysUserDto;
import com.y1jt.spzxmodel.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : YeJT
 * @date : 2023-11-23 16:41
 * @Description: 查询语句
 */
@Mapper
public interface SysUserMapper {
    SysUser selectUserInfoByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
