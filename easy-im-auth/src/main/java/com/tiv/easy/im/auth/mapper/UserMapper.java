package com.tiv.easy.im.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiv.easy.im.auth.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}