package com.tiv.easy.im.auth.data.user.update;

import lombok.Data;

@Data
public class UpdateUserInfoRequest {

    /**
     * id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别:0-男,1-女,2-保密
     */
    private Integer gender;

}
