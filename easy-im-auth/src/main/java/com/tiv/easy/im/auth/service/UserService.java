package com.tiv.easy.im.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tiv.easy.im.auth.data.user.register.RegisterRequest;
import com.tiv.easy.im.auth.data.user.register.RegisterResponse;
import com.tiv.easy.im.auth.model.User;

public interface UserService extends IService<User> {

    RegisterResponse register(RegisterRequest request);

}