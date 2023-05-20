package com.example.privmall.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

public class JwtTest {

    @Test
    @DisplayName(value = "1. 토큰이 잘 만들어진다")
    public void jwtTokenTest() {
        // JWT 생성
        Algorithm AL = Algorithm.HMAC256("hello");
        String token = JWT.create()
                .withSubject("revi1337")                                                        // payload 의 sub 클레임 추가
                .withClaim("exp", Instant.now().getEpochSecond() + 1)                // payload 의 exp 클레임 추가 (expired 로 대체 가능)
                .withArrayClaim("role", new String[]{"ROLE_ADMIN", "ROLE_USER"})          // payload 에 role 이름의 배열 클레임 추가 ()
                .sign(AL);                                                                      // 서명할 알고리즘 (헤더의 alg 클레임에 해당함)
        System.out.println(token);

        // JWT 디코딩 (서명 검증은하지않고 디코딩만 해본것. Header 와 Payload 를 미리 까볼 수 있음.)
        DecodedJWT decode = JWT.decode(token);

        Map<String, Claim> claims = decode.getClaims();              // payload 의 모든 클레임들을  MAP 으로 반환
        System.out.println("claims = " + claims);

        Claim alg = decode.getHeaderClaim("alg");               // 헤더의 alg 클레임
        Claim typ = decode.getHeaderClaim("typ");               // 헤더의 typ 클레임
        System.out.println("alg = " + alg);
        System.out.println("typ = " + typ);

        // JWT 서명 검증 (서명 검증까지 하고 디코딩까지 수행. --> 이게 근본)
        DecodedJWT verify = JWT.require(AL).build().verify(token);
        System.out.println("verify = " + verify.getToken());
    }
}
