package com.y1jt.spzxmanager.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.y1jt.commonservice.excetion.GuguiException;
import com.y1jt.spzxmanager.enums.RedisKeyEnum;
import com.y1jt.spzxmanager.mapper.SysUserMapper;
import com.y1jt.spzxmanager.service.SysUserService;
import com.y1jt.spzxmodel.dto.system.LoginDto;
import com.y1jt.spzxmodel.dto.system.SysUserDto;
import com.y1jt.spzxmodel.entity.system.SysUser;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import com.y1jt.spzxmodel.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : YeJT
 * @date : 2023-11-23 16:23
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        //============验证码校验==================
        //获取请求中验证码缓存key
        String captcha = loginDto.getCaptcha();
        String redisKey = RedisKeyEnum.USER_VALIDATE.getKey() + loginDto.getCodeKey();
        //获取验证码redis缓存信息
        String validateCode = redisTemplate.opsForValue().get(redisKey);
        //比较验证码
        if (CharSequenceUtil.isEmpty(validateCode) || !CharSequenceUtil.equalsIgnoreCase(captcha, validateCode)) {
            throw new GuguiException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //验证通过，删除验证码
        redisTemplate.delete(redisKey);
        //============用户名密码校验=================
        //1.查询数据库 获取到用户信息
        String userName = loginDto.getUserName();
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);

        //2.判断数据库中是否存在该用户
        if (Objects.isNull(sysUser)) {
            throw new GuguiException(ResultCodeEnum.LOGIN_ERROR);
        }
        //3.判断请求中的密码和数据库中的密码是否一致；不一致抛出异常
        String databasePassword = sysUser.getPassword();
        String inputPassword = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!Objects.equals(databasePassword, inputPassword)) {
            throw new GuguiException(ResultCodeEnum.LOGIN_ERROR);
        }
        //4.生成token,将用户信息加入缓存中
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        String key = RedisKeyEnum.USER_LOGIN.getKey() + token;
        redisTemplate.opsForValue().set(key, JSON.toJSONString(sysUser), 7, TimeUnit.DAYS);
        //5.返回token
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

/*    @Override
    public SysUser getUserInfo(String token) {
        String redisKey = RedisKeyEnum.USER_LOGIN.getKey() + token;
        String redisValue = redisTemplate.opsForValue().get(redisKey);
        return JSON.parseObject(redisValue, SysUser.class);
    }*/

    @Override
    public void logout(String token) {
        String redisKey = RedisKeyEnum.USER_LOGIN.getKey() + token;
        redisTemplate.delete(redisKey);
    }

    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum, pageSize);

        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        this.verifyUserNameRepeat(sysUser.getUserName());
        //密码加密
        String md5Password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(md5Password);
        sysUser.setStatus(1);
        sysUserMapper.saveSysUser(sysUser);
    }

    /**
     * 验证用户名是否重复
     *
     * @param userName 用户名
     */
    private void verifyUserNameRepeat(String userName) {
        //判断用户名是否重复加入
        SysUser dbSysUserInfo = sysUserMapper.selectUserInfoByUserName(userName);
        if (Objects.nonNull(dbSysUserInfo)) {
            throw new GuguiException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        this.verifyUserNameRepeat(sysUser.getUserName());
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }


}
