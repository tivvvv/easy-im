package com.tiv.easy.im.auth;

import com.tiv.easy.im.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testMyBatisPlus() {
        System.out.println(userService.count());
    }

}
