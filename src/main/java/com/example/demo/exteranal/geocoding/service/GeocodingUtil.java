package com.example.demo.exteranal.geocoding.service;

import com.example.demo.exteranal.geocoding.dto.GeocodingResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GeocodingUtil {

    @Value("${geocoding.client.id}")
    private String clientId;

    @Value("${geocoding.client.secret}")
    private String clientSecret;

    public GeocodingResponseDto getLocationByAddress(String address) {
        String endpoint = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
        String url = endpoint + "?query=" + address;

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.add("X-NCP-APIGW-API-KEY", clientSecret);

        RestTemplate restTemplate = new RestTemplate();

        // GET 요청
        ResponseEntity<GeocodingResponseDto> responseEntity = restTemplate.getForEntity(url, GeocodingResponseDto.class);

        GeocodingResponseDto geocodingResponseDto = responseEntity.getBody();

        String longitude = geocodingResponseDto.getLongitude();
        String latitude = geocodingResponseDto.getLatitude();

        System.out.println("latitude = " + latitude);
        System.out.println("longitude = " + longitude);

        return geocodingResponseDto;
    }

}
