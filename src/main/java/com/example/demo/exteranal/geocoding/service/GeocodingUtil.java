package com.example.demo.exteranal.geocoding.service;

import com.example.demo.exteranal.geocoding.dto.GeocodingResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GeocodingUtil {

    private final ObjectMapper objectMapper;

    @Value("${geocoding.client.id}")
    private String clientId;

    @Value("${geocoding.client.secret}")
    private String clientSecret;

    public GeocodingResponseDto getLocationByAddress(String address) throws IOException {
        String endpoint = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
        String url = endpoint + "?query=" + address;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String responseBody = responseEntity.getBody();

        Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

        List<Map<String, Object>> addressInfo = (List<Map<String, Object>>) responseMap.get("addresses");

        Map<String, Object> response = addressInfo.get(0);
        System.out.println("response = " + response);
        String x = (String) response.get("x");
        String y = (String) response.get("y");

        GeocodingResponseDto geocodingResponseDto = GeocodingResponseDto.builder()
                .latitude(x)
                .longitude(y)
                .build();

        return geocodingResponseDto;
    }
}
