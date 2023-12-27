package com.y1jt.spzxmanager.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.y1jt.commonutil.utils.AuthContextUtil;
import com.y1jt.spzxmanager.enums.RedisKeyEnum;
import com.y1jt.spzxmodel.entity.system.SysUser;
import com.y1jt.spzxmodel.vo.common.Result;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : YeJT
 * @date : 2023-11-29 17:14
 * @Description: TODO
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求方式 options则直接return
        if (Objects.equals(request.getMethod(), "OPTIONS")) {
            return true;
        }
        //获取token
        String token = request.getHeader("token");
        //token为空抛出异常
        if (CharSequenceUtil.isEmpty(token)) {
            this.responseNoLoginInfo(response);
            return false;
        }
        //不为空 查询redis获取用户信息
        String redisKey = RedisKeyEnum.USER_LOGIN.getKey() + token;
        String context = redisTemplate.opsForValue().get(redisKey);

        //用户信息为空 抛出异常 ；不为空 存储到ThreadLocal中
        if (CharSequenceUtil.isEmpty(context)) {
            this.responseNoLoginInfo(response);
            return false;
        }
        AuthContextUtil.set(JSON.parseObject(context, SysUser.class));
        //redis缓存延长时间
        redisTemplate.expire(redisKey, 30, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        AuthContextUtil.remove();
    }


    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }

}
