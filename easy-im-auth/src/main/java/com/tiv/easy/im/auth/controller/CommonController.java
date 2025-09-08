package com.tiv.easy.im.auth.controller;

import com.tiv.easy.im.auth.common.Result;
import com.tiv.easy.im.auth.data.user.sms.SmsRequest;
import com.tiv.easy.im.auth.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/common")
public class CommonController {

    @Resource
    private CommonService commonService;

    @GetMapping("/sms/code")
    public Result<Boolean> sendSmsCode(@Valid @RequestBody SmsRequest request) {
        return Result.success(commonService.sendSmsCode(request));
    }

}
