package com.y1jt.commonservice.excetion;

import com.y1jt.spzxmodel.vo.common.Result;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : YeJT
 * @date : 2023-11-29 10:30
 * @Description: TODO
 */
@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(Exception.class)
    public Result error() {
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(GuguiException.class)
    public Result error(GuguiException exception) {
        return Result.build(null, exception.getResultCodeEnum());
    }
}
