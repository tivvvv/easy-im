package com.tiv.easy.im.auth.controller;

import com.tiv.easy.im.auth.common.Result;
import com.tiv.easy.im.auth.data.user.login.LoginRequest;
import com.tiv.easy.im.auth.data.user.login.LoginResponse;
import com.tiv.easy.im.auth.data.user.register.RegisterRequest;
import com.tiv.easy.im.auth.data.user.register.RegisterResponse;
import com.tiv.easy.im.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
