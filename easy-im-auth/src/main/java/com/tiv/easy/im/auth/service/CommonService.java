package com.tiv.easy.im.auth.service;

import com.tiv.easy.im.auth.data.user.sms.SmsRequest;

public interface CommonService {

    Boolean sendSmsCode(SmsRequest request);

}
