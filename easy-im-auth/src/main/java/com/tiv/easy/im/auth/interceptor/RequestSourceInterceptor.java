package com.tiv.easy.im.auth.interceptor;

import com.tiv.easy.im.auth.common.CodeEnum;
import com.tiv.easy.im.auth.constants.AuthConstants;
import com.tiv.easy.im.auth.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求来源拦截器
 */
@Slf4j
@Component
public class RequestSourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(AuthConstants.REQUEST_SOURCE);
        if (!AuthConstants.GATEWAY.equals(header)) {
            throw new GlobalException(CodeEnum.INVALID_REQUEST_SOURCE_ERROR);
        }

        return true;
    }

}
