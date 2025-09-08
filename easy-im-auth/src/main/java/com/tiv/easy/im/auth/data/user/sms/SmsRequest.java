package com.tiv.easy.im.auth.data.user.sms;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class SmsRequest implements Serializable {

    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号应为11位")
    private String phone;

}
