package com.example.demo.api.token.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.api.token.dto.AccessTokenResponseDto;
import com.example.demo.global.jwt.constant.GrantType;
import com.example.demo.global.jwt.service.TokenManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final MemberService memberService;
    private final TokenManager tokenManager;
    public AccessTokenResponseDto createAccessTokenByRefreshToken(String refreshToken) {
        Member member = memberService.findMemberByRefreshToken(refreshToken);
        Date accessTokenExpireTime = tokenManager.createAccessTokenExpireTime();
        String accessToken = tokenManager.createAccessToken(member.getMemberId(), member.getRole(), accessTokenExpireTime);

        return AccessTokenResponseDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .build();
    }
}
