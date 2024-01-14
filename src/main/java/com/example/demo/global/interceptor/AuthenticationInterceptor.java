package com.example.demo.global.interceptor;

import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.AuthenticationException;
import com.example.demo.global.jwt.constant.TokenType;
import com.example.demo.global.jwt.service.TokenManager;
import com.example.demo.global.util.AuthorizationHeaderUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    //컨트롤러 로직을 시작하기 전에 확인하는 로직
    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. Authorization Header 검증
        String authorizationHeader = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);//헤더가 빈값인지, Bearer가 붙어 있는지 확인

        // 2. 토큰 검증
        String token = authorizationHeader.split(" ")[1];
        tokenManager.validateToken(token);//유효한 토큰인지 확인 - 서명 확인

        // 3. 토큰 타입 - 엑세스 인지.
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        String tokenType = tokenClaims.getSubject();
        if (!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        return true;
    }
}
