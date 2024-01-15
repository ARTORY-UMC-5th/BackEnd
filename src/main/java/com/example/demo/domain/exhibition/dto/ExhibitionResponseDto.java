package com.example.demo.domain.exhibition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ExhibitionResponseDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExhibitionRecentResponseDto {
        private Long id;
        private String exhibitionTitle;
        private String exhibitionImage;
    }

}