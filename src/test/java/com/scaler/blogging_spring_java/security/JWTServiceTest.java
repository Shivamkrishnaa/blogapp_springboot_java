package com.scaler.blogging_spring_java.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTServiceTest {
    private final JWTService jwtService = new JWTService();
    @Test
    void canCreateJWTFromUserId() {
        Long userId = 1L;
        String JWTToken = jwtService.createJwt(userId);
        System.out.println(JWTToken+" shivm");
        Assertions.assertNotNull(JWTToken);
    }

    @Test
    void canVerifyJWTFromUserId() {
        Long userId = 1L;
        String JWTToken = jwtService.createJwt(userId);
        Assertions.assertEquals(userId, jwtService.retrieveUserId(JWTToken));
    }
}
