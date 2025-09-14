package com.tiv.easy.im.auth.service.impl;

import com.tiv.easy.im.auth.common.CodeEnum;
import com.tiv.easy.im.auth.constants.UserConstants;
import com.tiv.easy.im.auth.data.common.file.FileUploadUrlResponse;
import com.tiv.easy.im.auth.data.common.sms.SmsRequest;
import com.tiv.easy.im.auth.exception.GlobalException;
import com.tiv.easy.im.auth.service.CommonService;
import com.tiv.easy.im.auth.utils.MinioUtil;
import com.tiv.easy.im.auth.utils.SmsUtil;
import com.tiv.easy.im.auth.utils.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private MinioUtil minioUtil;

    @Override
    public Boolean sendSmsCode(SmsRequest request) {
        String phone = request.getPhone();
        String code = VerificationCodeGenerator.getRandomNum();
        stringRedisTemplate.opsForValue().set(UserConstants.USER_PREFIX + phone, code, 5, TimeUnit.MINUTES);
        try {
            SmsUtil.mockSendSms(phone, code);
            log.info("手机号: {}, 验证码: {}", phone, code);
        } catch (Exception e) {
            log.error("发送短信失败", e);
            throw new GlobalException(CodeEnum.SEND_CODE_ERROR);
        }
        return null;
    }

    @Override
    public FileUploadUrlResponse getFileUploadUrl(String fileName) {
        String uploadUrl = minioUtil.uploadUrl(fileName, 60);
        String downUrl = minioUtil.downUrl(fileName);

        return FileUploadUrlResponse.builder()
                .uploadUrl(uploadUrl)
                .downloadUrl(downUrl)
                .build();
    }

}
