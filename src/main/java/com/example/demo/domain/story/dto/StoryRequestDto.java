package com.example.demo.domain.story.dto;

import com.example.demo.domain.member.constant.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class StoryRequestDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoryRequestGeneralDto {


        // exhibition 정보
        private Long exhibitionId;

        private String storyTitle;
        private String storySatisfactionLevel;
        private String storyWeather;
        private String storyCompanion;
        private String storyKeyword;
        private String storyViewingTime;
        private int year;
        private int month;
        private int day;
        @Lob
        @Column(length = 1000000)
        private String storyContext;


        private String genre1;
        private String genre2;
        private String genre3;


        private Boolean isOpen;

        private List<String> picturesUrl;


    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoryRequestDateDto {


        // exhibition 정보
        private Long exhibitionId;

        private int year;
        private int month;
        private int day;



    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoryRequestDraftDto {


        // exhibition 정보
        private Long exhibitionId;

        private Long storyId;
        private String storyTitle;
        private String storySatisfactionLevel;
        private String storyWeather;
        private String storyCompanion;
        private String storyKeyword;
        private String storyViewingTime;
        private int year;
        private int month;
        private int day;
        @Lob
        @Column(length = 1000000)
        private String storyContext;

        @Builder.Default
        private Genre genre1 = Genre.NONE;

        @Builder.Default
        private Genre genre2 = Genre.NONE;

        @Builder.Default
        private Genre genre3 = Genre.NONE;


        private Boolean isOpen;

        private List<String> picturesUrl;


    }
}
