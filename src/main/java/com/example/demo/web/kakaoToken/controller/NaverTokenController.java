package com.example.demo.web.kakaoToken.controller;

import com.example.demo.api.login.dto.OauthLoginDto;
import com.example.demo.api.login.service.OauthLoginService;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.web.kakaoToken.client.KakaoTokenClient;
import com.example.demo.web.kakaoToken.client.NaverTokenClient;
import com.example.demo.web.kakaoToken.dto.KakaoTokenDto;
import com.example.demo.web.kakaoToken.dto.NaverTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
public class NaverTokenController {

    private final NaverTokenClient naverTokenClient;
    private final OauthLoginService oauthLoginService;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Value("${naver.callback.url}")
    private String callbackUrl;



    @GetMapping("login/oauth2/code/naver")//콜백 주소
    public @ResponseBody OauthLoginDto.Response naverCallback(@RequestParam(name = "code", required = true) String code,
                                                              @RequestParam(name = "state", required = true) String state) throws UnsupportedEncodingException {

        String redirectURI = URLEncoder.encode(callbackUrl, "UTF-8");
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        NaverTokenDto.Request naverTokenRequestDto = NaverTokenDto.Request.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
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
