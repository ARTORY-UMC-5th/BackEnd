package com.example.demo.web.kakaoToken.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "소셜 로그인", description = "서버 콜백 API (서버 내부용)")
@Controller
@RequiredArgsConstructor
public class SocialTokenController {


    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.client.secret}")
    private String kakaoClientSecret;

    @Value("${kakao.callback.url}")
    private String KakaoCallbackUrl;

    @Value("${naver.client.id}")
    private String NaverClientId;

    @Value("${naver.client.secret}")
    private String NaverClientSecret;

    @Value("${naver.callback.url}")
    private String NaverCallbackUrl;

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/kakao")
    public String kakaoLogin() {
        String redirectUrl = "https://kauth.kakao.com/oauth/authorize?client_id="
                +kakaoClientId
                +"&redirect_uri="
                +KakaoCallbackUrl
                +"&response_type=code";

        return "redirect:"+redirectUrl;
    }
    @GetMapping("/naver")
    public String naverLogin() {
        String redirectUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="
                +NaverClientId
                +"&redirect_uri="
                +NaverCallbackUrl
                +"&state=YOUR_RANDOM_STATE"
                +"&response_type=code";

        return "redirect:"+redirectUrl;
    }





}