package com.example.demo.global.interceptor;

import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.AuthenticationException;
import com.example.demo.global.jwt.constant.TokenType;
import com.example.demo.global.jwt.service.TokenManager;
import com.example.demo.global.util.AuthorizationHeaderUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    //컨트롤러 로직을 시작하기 전에 확인하는 로직
    private final TokenManager tokenManager;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isPreflightRequest(request)) {
            return true;
        }
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
//        // 4. 리프레시 토큰 만료
//        Long memberId = Long.valueOf( (Integer) tokenClaims.get("memberId"));
//        tokenManager.validateToken(memberRepository.findByMemberId(memberId).getRefreshToken());


        return true;
    }
    private boolean isPreflightRequest(HttpServletRequest request) {
        return isOptions(request) && hasHeaders(request) && hasMethod(request) && hasOrigin(request);
    }

    private boolean isOptions(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString());
    }

    private boolean hasHeaders(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Access-Control-Request-Headers"));
    }

    private boolean hasMethod(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Access-Control-Request-Method"));
    }

    private boolean hasOrigin(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Origin"));
    }
}
