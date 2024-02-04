package com.example.demo.web.kakaoToken.client;

import com.example.demo.web.kakaoToken.dto.KakaoTokenDto;
import com.example.demo.web.kakaoToken.dto.NaverTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://nid.naver.com", name = "naverTokenClient")
public interface NaverTokenClient {
    @PostMapping(value = "/oauth2.0/token", consumes = "application/json")
    NaverTokenDto.Response requestNaverToken(@RequestHeader("Content-Type") String contentType, @SpringQueryMap NaverTokenDto.Request request);
}
