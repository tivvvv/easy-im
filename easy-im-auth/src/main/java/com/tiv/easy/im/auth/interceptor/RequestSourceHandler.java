package com.tiv.easy.im.auth.interceptor;

import com.alibaba.fastjson.JSON;
import com.tiv.easy.im.auth.common.CodeEnum;
import com.tiv.easy.im.auth.common.Result;
import com.tiv.easy.im.auth.constants.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求来源处理器
 */
@Slf4j
@Component
public class RequestSourceHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(AuthConstants.REQUEST_SOURCE);
        if (!AuthConstants.GATEWAY.equals(header)) {
            refuseResult(response);
            return false;
        }
        return true;
    }

    private void refuseResult(HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        Result<Object> result = Result.error(CodeEnum.INVALID_REQUEST_SOURCE);
        response.getWriter().print(JSON.toJSONString(result));
        response.getWriter().flush();
    }

}
