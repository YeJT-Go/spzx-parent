package com.y1jt.spzxmanager.service;

import com.y1jt.spzxmodel.vo.system.ValidateCodeVo;

/**
 * @author : YeJT
 * @date : 2023-11-29 14:05
 * @Description: 生成验证码
 */
public interface ValidateCodeService {

    /**
     * 生成验证码
     *
     * @return 验证码信息
     */
    ValidateCodeVo generateValidateCode();
}
