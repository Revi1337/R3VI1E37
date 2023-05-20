package com.example.privmall.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.privmall.dto.response.TokenVerifyResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private static final String SECRET = "DUMMY";

    private static final Algorithm AL = Algorithm.HMAC512(SECRET);

    private static final long LIFE_TIME = 60 * 60;

    public String generate(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withClaim("exp", Instant.now().getEpochSecond() + LIFE_TIME)
                .sign(AL);
    }

    public TokenVerifyResponse verify(String token) {
        try {
            DecodedJWT decode = JWT.require(AL).build().verify(token);
            return new TokenVerifyResponse(decode.getSubject(), true);
        } catch (JWTVerificationException exception) {
            DecodedJWT decode = JWT.decode(token);
            return new TokenVerifyResponse(decode.getSubject(), false);
        }
    }

}
