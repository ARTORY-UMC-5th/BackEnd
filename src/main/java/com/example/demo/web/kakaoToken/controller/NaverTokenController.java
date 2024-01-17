package com.example.demo.web.kakaoToken.controller;

import com.example.demo.api.login.dto.OauthLoginDto;
import com.example.demo.api.login.service.OauthLoginService;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.web.kakaoToken.client.NaverTokenClient;
import com.example.demo.web.kakaoToken.dto.NaverTokenDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Tag(name = "소셜 로그인", description = "서버 콜백 API (서버 내부용)")
@Controller
@RequiredArgsConstructor
public class NaverTokenController {

    private final NaverTokenClient naverTokenClient;
    private final OauthLoginService oauthLoginService;

    @Value("${naver.client.id}")
    private String NaverClientId;

    @Value("${naver.client.secret}")
    private String NaverClientSecret;

    @Value("${naver.callback.url}")
    private String NaverCallbackUrl;



    @GetMapping("login/oauth2/code/naver")//콜백 주소
    public @ResponseBody OauthLoginDto.Response naverCallback(@RequestParam(name = "code", required = true) String code,
                                                              @RequestParam(name = "state", required = true) String state) throws UnsupportedEncodingException {

        String redirectURI = URLEncoder.encode(NaverCallbackUrl, "UTF-8");
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        NaverTokenDto.Request naverTokenRequestDto = NaverTokenDto.Request.builder()
                .client_id(NaverClientId)
                .client_secret(NaverClientSecret)
                .grant_type("authorization_code")
                .code(code)
                .state(state)
                .redirect_uri(redirectURI)
                .build();
        System.out.println("CODE : " + code);

        NaverTokenDto.Response naverToken = naverTokenClient.requestNaverToken(contentType, naverTokenRequestDto);

        System.out.println("getAccess_token : " + naverToken.getAccess_token());

        System.out.println(naverToken);

        return oauthLoginService.oauthLogin(naverToken.getAccess_token(), MemberType.from("NAVER"));

    }

}
