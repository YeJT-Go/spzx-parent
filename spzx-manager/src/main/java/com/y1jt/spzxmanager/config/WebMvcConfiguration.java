package com.y1jt.spzxmanager.config;

import com.y1jt.spzxmanager.interceptor.LoginAuthInterceptor;
import com.y1jt.spzxmanager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : YeJT
 * @date : 2023-11-29 10:52
 * @Description:
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private UserProperties userProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(loginAuthInterceptor)
                //排除接口（不拦截的接口）
                .excludePathPatterns(userProperties.getNoAuthUrls())
//                .excludePathPatterns("/doc.html#", "/swagger-ui.html/**", "/swagger-ui/index.html#/", "/swagger-resources/**")
                .addPathPatterns("/**");
    }

    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加路径规则
        registry.addMapping("/**")
                //是否允许在跨域的情况下传递cookie
                .allowCredentials(true)
                //允许请求来源的域规则
                .allowedOriginPatterns("*")
                //将 HTTP 方法设置为允许，例如 "GET"， "POST"， 等。
                //特殊值 "*" 允许所有方法。
                //默认情况下，“简单”方法 GET、 HEAD和 POST 是允许的
                .allowedMethods("*")
                //设置预检请求可以列出的标头列表，以允许在实际请求期间使用。
                //特殊值 "*" 可用于允许所有标头。
                //如果标头名称是以下项之一，则不需要列出标头名称：Cache-Control、、、ExpiresContent-LanguageLast-Modified、或Pragma根据 CORS 规范。
                //默认情况下，允许使用所有标头。
                .allowedHeaders("*")
                .maxAge(1800);
    }
}
