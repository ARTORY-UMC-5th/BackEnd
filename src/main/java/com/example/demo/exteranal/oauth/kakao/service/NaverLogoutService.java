package com.example.demo.exteranal.oauth.kakao.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class NaverLogoutService {
    @Value("${naver.client.id}")
    private String NaverClientId;

    @Value("${naver.client.secret}")
    private String NaverClientSecret;
    public void naverLogout(String accessToken){
        // TODO: 실제 사용할 클라이언트 ID, 클라이언트 시크릿, 액세스 토큰을 설정하세요.
        String clientId = NaverClientId;
        String clientSecret = NaverClientSecret;

        // Naver API 엔드포인트 URL
        String apiUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete" +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&access_token=" + accessToken +
                "&service_provider=NAVER";

        // URI 생성
        URI uri;
        try {
            uri = new URI(apiUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to create URI", e);
        }

        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // DELETE 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.execute(uri, HttpMethod.DELETE, (RequestCallback) null, (ResponseExtractor<ResponseEntity<String>>) null);

        // 응답 출력
        System.out.println("Response Status: " + responseEntity.getStatusCode());
        System.out.println("Response Body: " + responseEntity.getBody());
    }


}
