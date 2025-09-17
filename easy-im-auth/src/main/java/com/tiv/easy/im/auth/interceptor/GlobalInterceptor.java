package com.tiv.easy.im.auth.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 全局拦截器
 */
@Configuration
public class GlobalInterceptor implements WebMvcConfigurer {

    @Resource
    private RequestSourceHandler requestSourceHandler;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestSourceHandler)
                .addPathPatterns("/**");
    }

}
