package com.example.demo.exteranal.oauth.kakao.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
@Service
public class KakaoLogoutService {

    public void kakaoLogout(String accessToken){
        // TODO: ACCESS_TOKEN 변수에 실제로 사용할 액세스 토큰을 설정하세요.

        // Kakao API 엔드포인트 URL
        String apiUrl = "https://kapi.kakao.com/v1/user/logout";
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);
        // RequestEntity 생성
        RequestEntity<Void> requestEntity;
        try {
            requestEntity = RequestEntity.post(new URI(apiUrl)).headers(headers).build();
        } catch (
                URISyntaxException e) {
            throw new RuntimeException("Failed to create RequestEntity", e);
        }
        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();
        // API 호출 및 응답 수신
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        // 응답 출력
        System.out.println("Response Status: " + responseEntity.getStatusCode());
        System.out.println("Response Body: " + responseEntity.getBody());


    }

}
