package com.tiv.easy.im.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiv.easy.im.auth.mapper.UserMapper;
import com.tiv.easy.im.auth.model.User;
import com.tiv.easy.im.auth.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}