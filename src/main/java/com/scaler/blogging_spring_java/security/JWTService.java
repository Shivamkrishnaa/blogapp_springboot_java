package com.scaler.blogging_spring_java.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String JWT_KEY = "hello_world";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJwt(Long userId) {
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public Long retrieveUserId(String jwtString) {
        var decodedJWT = JWT.decode(jwtString);
        return Long.valueOf(decodedJWT.getSubject());
    }


}
