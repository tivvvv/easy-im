package com.tiv.easy.im.auth.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiv.easy.im.auth.common.CodeEnum;
import com.tiv.easy.im.auth.constants.UserConstants;
import com.tiv.easy.im.auth.data.user.register.RegisterRequest;
import com.tiv.easy.im.auth.data.user.register.RegisterResponse;
import com.tiv.easy.im.auth.exception.GlobalException;
import com.tiv.easy.im.auth.mapper.UserMapper;
import com.tiv.easy.im.auth.model.User;
import com.tiv.easy.im.auth.service.UserService;
import com.tiv.easy.im.auth.utils.NicknameGenerator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String phone = request.getPhone();
        String password = request.getPassword();

        // 判断用户是否已注册
        if (isRegistered(phone)) {
            throw new GlobalException(CodeEnum.REGISTER_ERROR);
        }

        // 查询redis中验证码
        String verifyCode = stringRedisTemplate.opsForValue().get(UserConstants.REGISTER_PREFIX + phone);
        if (verifyCode == null || !verifyCode.equals(request.getCode())) {
            throw new GlobalException(CodeEnum.CODE_ERROR);
        }

        // 雪花算法
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);

        long userId = snowflake.nextId();
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        User user = User.builder()
                .userId(userId)
                .password(encryptedPassword)
                .phone(phone)
                .userName(NicknameGenerator.generateNickname())
                .build();
        boolean saved = this.save(user);
        if (!saved) {
            throw new GlobalException(CodeEnum.SAVE_USER_ERROR);
        }

        return new RegisterResponse(userId);
    }

    private boolean isRegistered(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        long count = this.count(queryWrapper);
        return count > 0;
    }

}