package com.example.demo.domain;

import com.example.demo.exteranal.geocoding.dto.GeocodingResponseDto;
import com.example.demo.exteranal.geocoding.service.GeocodingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor

public class GeoController {

    private final GeocodingUtil geocodingUtil;

    @GetMapping("/geocoding")
    public void test(String address) {
        geocodingUtil.getLocationByAddress(address);
    }

}
