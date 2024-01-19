package com.example.demo.domain.exhibition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TestExhibitionResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExhibitionGeneralResponseDto {
        private Long id;
        private String exhibitionTitle;
        private String exhibitionImage;
        private boolean isLiked; //전시회 좋아하는지

    }
}
