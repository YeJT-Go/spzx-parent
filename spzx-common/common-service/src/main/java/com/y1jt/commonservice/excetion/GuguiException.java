package com.y1jt.commonservice.excetion;

import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author : YeJT
 * @date : 2023-11-29 10:31
 * @Description: TODO
 */
@Data
public class GuguiException extends RuntimeException {

    private Integer code;

    private String message;

    private ResultCodeEnum resultCodeEnum;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public GuguiException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
