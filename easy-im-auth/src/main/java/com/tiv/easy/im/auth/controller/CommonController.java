package com.tiv.easy.im.auth.controller;

import com.tiv.easy.im.auth.common.Result;
import com.tiv.easy.im.auth.data.common.file.FileUploadUrlResponse;
import com.tiv.easy.im.auth.data.common.sms.SmsRequest;
import com.tiv.easy.im.auth.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取文件上传和下载的url
     *
     * @param fileName 文件名
     * @return
     */
    @GetMapping("/file/upload/{fileName}")
    public Result<FileUploadUrlResponse> getFileUploadUrl(@PathVariable String fileName) {
        return Result.success(commonService.getFileUploadUrl(fileName));
    }

}
