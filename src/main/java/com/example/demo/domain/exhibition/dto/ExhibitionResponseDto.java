package com.example.demo.domain.exhibition.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;


public class ExhibitionResponseDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExhibitionGeneralResponseDto {
        private Long ExhibitionId;
        private String exhibitionTitle;
        private String exhibitionImage;
        private boolean isLiked;
        private boolean isScrapped;

    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExhibitionGeneralOneResponseDto {
        private Long ExhibitionId;
        private String exhibitionTitle;
        private String exhibitionImage;


    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExhibitionSpecificResponseDto {
        private Long ExhibitionId;
        private String exhibitionTitle; //제목, 크롤링기준: TITLE
        private String exhibitionImage; //이미지 Url, 크롤링기준: MAIN_IMG
        private String exhibitionAddress; //자치구, 크롤링기준: GUNAME
        private String exhibitionPlace; //장소, 크롤링기준: PLACE
        private String exhibitionDuration; //기간, 크롤링기준: DATE
        private String exhibitionInstitution; //기관명, 크롤링기준: ORG_NAME
        private String exhibitionViewingTime; //관람시간, 유저가 입력

        private String exhibitionViewingAge; //관람연령, 크롤링기준: USE_TRGT
        private String exhibitionPrice; //가격, 크롤링기준: USE_FEE

        @Column(length = 800)
        private String exhibitionUrl; //전시회 사이트, 크롤링 기준: ORG_LINK
        private String exhibitionLongitude; //경도, 크롤링기준: LOT
        private String exhibitionLatitude; //위도, 크롤링기준: LAT

        private String exhibitionStartDate; //시작날짜, 크롤링 기준: STRTDATE
        private String exhibitionEndDate; //종료날짜, 크롤링 기준: END_DATE
        @Builder.Default
        private boolean isEnded = false; //전시회 종료되었는지, 안되었는지



        private String genreCategory1;
        private String genreCategory2;
        private String genreCategory3;


    }


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExhibitionListResponseDto {
        private List<ExhibitionGeneralResponseDto> recentExhibitionDtoList;
        private List<ExhibitionGeneralResponseDto> popluarExhibitionDtoList;
        private List<ExhibitionGeneralResponseDto> distanceRecommendExhibitionDtoList;
        private List<ExhibitionGeneralResponseDto> randomExhibitionDtoList;
        private List<ExhibitionGeneralResponseDto> recommendExhibitionDtoList;
        private List<ExhibitionGeneralResponseDto> similarExhibitionDtoList;


    }

}
