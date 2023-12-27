package com.y1jt.spzxmanager.service;

import com.github.pagehelper.PageInfo;
import com.y1jt.spzxmodel.dto.system.LoginDto;
import com.y1jt.spzxmodel.dto.system.SysUserDto;
import com.y1jt.spzxmodel.entity.system.SysUser;
import com.y1jt.spzxmodel.vo.system.LoginVo;

/**
 * @author : YeJT
 * @date : 2023-11-23 16:23
 * @Description: TODO
 */
public interface SysUserService {


    LoginVo login(LoginDto loginDto);

//    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
