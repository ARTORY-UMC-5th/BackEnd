package com.example.demo.exteranal.geocoding.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeocodingResponseDto {

    private String latitude;
    private String longitude;
}
