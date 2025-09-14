package com.tiv.easy.im.auth.service;

import com.tiv.easy.im.auth.data.common.file.FileUploadUrlResponse;
import com.tiv.easy.im.auth.data.common.sms.SmsRequest;

public interface CommonService {

    Boolean sendSmsCode(SmsRequest request);

    FileUploadUrlResponse getFileUploadUrl(String fileName);

}
