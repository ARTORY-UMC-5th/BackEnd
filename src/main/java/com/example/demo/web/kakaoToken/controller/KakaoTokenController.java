package com.example.demo.web.kakaoToken.controller;

import com.example.demo.api.login.dto.OauthLoginDto;
import com.example.demo.api.login.service.OauthLoginService;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.web.kakaoToken.client.KakaoTokenClient;
import com.example.demo.web.kakaoToken.dto.KakaoTokenDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Tag(name = "소셜 로그인", description = "서버 콜백 API (서버 내부용)")
@Controller
@RequiredArgsConstructor
public class KakaoTokenController {

    private final KakaoTokenClient kakaoTokenClient;
    private final OauthLoginService oauthLoginService;

    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.client.secret}")
    private String kakaoClientSecret;



    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/oauth/kakao/callback")
    public @ResponseBody OauthLoginDto.Response loginCallback(String code) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoTokenDto.Request kakaoTokenRequestDto = KakaoTokenDto.Request.builder()
                .client_id(kakaoClientId)
                .client_secret(kakaoClientSecret)
                .grant_type("authorization_code")
                .code(code)
                .redirect_uri("http://3.39.39.6:8080/oauth/kakao/callback")
//                .redirect_uri("http://localhost:8080/home")

                .build();
        System.out.println("CODE : " + code);

        KakaoTokenDto.Response kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);

        System.out.println("getAccess_token : " + kakaoToken.getAccess_token());

        System.out.println(kakaoToken);
//        return "kakao toekn : " + kakaoToken;
        return oauthLoginService.oauthLogin(kakaoToken.getAccess_token(), MemberType.from("KAKAO"));
    }


}
