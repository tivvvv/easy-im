package com.tiv.easy.im.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tiv.easy.im.auth.data.user.login.LoginRequest;
import com.tiv.easy.im.auth.data.user.login.LoginResponse;
import com.tiv.easy.im.auth.data.user.register.RegisterRequest;
import com.tiv.easy.im.auth.data.user.register.RegisterResponse;
import com.tiv.easy.im.auth.model.User;

public interface UserService extends IService<User> {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    default User getOnly(QueryWrapper<User> wrapper, boolean throwEx) {
        wrapper.last("limit 1");

        return this.getOne(wrapper, throwEx);
    }

}