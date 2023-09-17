package com.scaler.blogging_spring_java.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class JWTAuthenticationConvertor implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || authHeader.startsWith("Bearer ")) {
            return null;
        }
        var jwt = authHeader.substring(7);
        return new JWTAuthentication(jwt);
    }
}
