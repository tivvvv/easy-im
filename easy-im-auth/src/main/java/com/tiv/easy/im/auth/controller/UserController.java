package com.tiv.easy.im.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.tiv.easy.im.auth.common.CodeEnum;
import com.tiv.easy.im.auth.common.Result;
import com.tiv.easy.im.auth.data.user.login.LoginByCodeRequest;
import com.tiv.easy.im.auth.data.user.login.LoginRequest;
import com.tiv.easy.im.auth.data.user.login.LoginResponse;
import com.tiv.easy.im.auth.data.user.register.RegisterRequest;
import com.tiv.easy.im.auth.data.user.register.RegisterResponse;
import com.tiv.easy.im.auth.data.user.update.UpdateUserInfoRequest;
import com.tiv.easy.im.auth.exception.GlobalException;
import com.tiv.easy.im.auth.service.UserService;
import com.tiv.easy.im.auth.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = userService.register(request);

        return Result.success(response);
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);

        return Result.success(response);
    }

    @PostMapping("/login/code")
    public Result<LoginResponse> loginByCode(@Valid @RequestBody LoginByCodeRequest request) {
        LoginResponse response = userService.loginByCode(request);

        return Result.success(response);
    }

    @PostMapping("/update/info")
    public Result<LoginResponse> updateUserInfo(@RequestHeader String token, @Valid @RequestBody UpdateUserInfoRequest request) {
        String userId = JwtUtil.parse(token).getSubject();
        if (!StrUtil.isNumeric(userId)) {
            throw new GlobalException(CodeEnum.NOT_LOGIN_ERROR);
        }
        LoginResponse response = userService.updateUserInfo(Long.valueOf(userId), request);

        return Result.success(response);
    }

}
