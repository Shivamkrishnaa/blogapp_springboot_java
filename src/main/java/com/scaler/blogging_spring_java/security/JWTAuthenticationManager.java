package com.scaler.blogging_spring_java.security;

import com.scaler.blogging_spring_java.users.UserEntity;
import com.scaler.blogging_spring_java.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JWTAuthenticationManager implements AuthenticationManager {
    private final JWTService jwtService;
    private final UsersService usersService;

    public JWTAuthenticationManager(JWTService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication jwtAuthentication) {
            var jwt = jwtAuthentication.getCredentials();
            Long userId = jwtService.retrieveUserId(jwt);
            jwtAuthentication.setUser(usersService.getUser(userId));
            jwtAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
            return jwtAuthentication;
        }
        throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
    }

}
