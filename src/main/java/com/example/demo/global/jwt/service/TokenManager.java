package com.example.demo.global.jwt.service;

import com.example.demo.domain.member.constant.Role;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.AuthenticationException;
import com.example.demo.global.jwt.constant.GrantType;
import com.example.demo.global.jwt.constant.TokenType;
import com.example.demo.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String tokenSecret;

    public JwtTokenDto createJwtTokenDto(Long memberId, Role role, Boolean enrolled) {
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(memberId, role, accessTokenExpireTime);
        String refreshToken = createRefreshToken(memberId, refreshTokenExpireTime);
        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .enrolled(enrolled)
                .build();
    }

    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(Long memberId, Role role, Date expirationTime) {
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())    // 토큰 제목
                .setIssuedAt(new Date())                // 토큰 발급 시간
                .setExpiration(expirationTime)          // 토큰 만료 시간
                .claim("memberId", memberId)      // 회원 아이디
                .claim("role", role)              // 유저 role
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
        return accessToken;
    }

    public String createRefreshToken(Long memberId, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())   // 토큰 제목
                .setIssuedAt(new Date())                // 토큰 발급 시간
                .setExpiration(expirationTime)          // 토큰 만료 시간
                .claim("memberId", memberId)      // 회원 아이디
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
        return refreshToken;
    }

    public void validateToken(String token) {
        try {
            System.out.println("valdation Checking");
            Jwts.parserBuilder().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }
    public void refreshValidateToken(String token) {
        try {
            System.out.println("refresh valdation Checking");
            Jwts.parserBuilder().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.info("refresh token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.info("유효하지 않은 refresh token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    public Claims getTokenClaims(String token) {
        Claims claims;
        try {//신버전 문법 .parserBuilder()사용
            claims = Jwts.parserBuilder()
                    .setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
        return claims;
    }
//public void validateToken(String token) {
//    try {
//        Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
//                .parseClaimsJws(token);
//    } catch (ExpiredJwtException e) {
//        log.info("token 만료", e);
//        throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
//    } catch (Exception e) {
//        log.info("유효하지 않은 token", e);
//        throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
//    }
//}
//
//    public Claims getTokenClaims(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
//                    .parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            log.info("유효하지 않은 token", e);
//            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
//        }
//        return claims;
//    }

}
