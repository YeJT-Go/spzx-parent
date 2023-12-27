package com.y1jt.spzxmanager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.y1jt.spzxmanager.enums.RedisKeyEnum;
import com.y1jt.spzxmanager.service.ValidateCodeService;
import com.y1jt.spzxmodel.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : YeJT
 * @date : 2023-11-29 14:06
 * @Description: TODO
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private static final String PREFIX_KEY = "user:validate";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        //生成验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);
        //4位验证码数字
        String validateCode = circleCaptcha.getCode();
        //图片的Base64信息
        String imageBase64 = circleCaptcha.getImageBase64();
        //生成缓存key
        String validateKey = UUID.randomUUID().toString().replaceAll("-", "");
        String redisKey = RedisKeyEnum.USER_VALIDATE.getKey() + validateKey;
        //加入缓存
        redisTemplate.opsForValue().set(redisKey, validateCode, 3, TimeUnit.MINUTES);
        String codeValue = "data:image/png;base64," + imageBase64;
        //返回验证码信息
        return new ValidateCodeVo(validateKey, codeValue);
    }
}
