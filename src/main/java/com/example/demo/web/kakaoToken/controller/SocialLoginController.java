package com.example.demo.web.kakaoToken.controller;

import com.example.demo.api.login.dto.OauthLoginDto;
import com.example.demo.api.login.service.OauthLoginService;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.web.kakaoToken.dto.KakaoTokenDto;
import com.example.demo.web.kakaoToken.dto.NaverTokenDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "소셜 로그인", description = "클라이언트 서버 로그인용")
@Controller
@RequiredArgsConstructor
public class SocialLoginController {
    private final OauthLoginService oauthLoginService;
    @GetMapping("/naverlogin")
    public @ResponseBody OauthLoginDto.Response naverlogin(String accessToken) {
        return oauthLoginService.oauthLogin(accessToken, MemberType.from("NAVER"));
    }

    @GetMapping("/kakaologin")
    public @ResponseBody OauthLoginDto.Response kakaologin(String accessToken) {
        return oauthLoginService.oauthLogin(accessToken, MemberType.from("KAKAO"));
    }




}