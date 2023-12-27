package com.y1jt.commonutil.utils;

import com.y1jt.spzxmodel.entity.system.SysUser;

/**
 * @author : YeJT
 * @date : 2023-11-29 17:08
 * @Description: TODO
 */
public class  AuthContextUtil {

    //创建ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    /**
     * 添加数据
     */
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    /**
     * 获取数据
     */
    public static SysUser get() {
        return threadLocal.get();
    }

    /**
     * 删除数据
     */
    public static void remove() {
        threadLocal.remove();
    }

}
