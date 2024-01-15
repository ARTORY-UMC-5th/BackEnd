package com.example.demo.exteranal.oauth.kakao.client;

import com.example.demo.exteranal.oauth.kakao.dto.KakaoUserInfoResponseDto;
import com.example.demo.exteranal.oauth.kakao.dto.NaverUserInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://openapi.naver.com", name = "naverUserInfoClient")
public interface NaverUserInfoClient {
    @GetMapping(value = "/v1/nid/me", consumes = "application/json")
    NaverUserInfoResponseDto getNaverUserInfo(@RequestHeader("Content-type") String contentType,
                                              @RequestHeader("Authorization") String accessToken);

}