package com.example.demo.domain.story.dto;

import com.example.demo.domain.member.constant.Genre;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;

public class StoryRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StorySaveRequestDto {

        // exhibition 정보
        private Long exhibitionId;

        private String storyTitle;
        private String storySatisfactionLevel;
        private String storyWeather;
        private String storyCompanion;
        private String storyKeyword;
        private String storyViewingTime;

        @Lob
        private String storyContext;

        @Builder.Default
        private Genre genre1 = null;

        @Builder.Default
        private Genre genre2 = null;

        @Builder.Default
        private Genre genre3 = null;

        private Boolean isOpen;

        private List<String> pictures;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoryUpdateRequestDto {

        private String storyTitle;
        private String storySatisfactionLevel;
        private String storyWeather;
        private String storyCompanion;
        private String storyKeyword;
        private String storyViewingTime;

        @Lob
        private String storyContext;

        @Builder.Default
        private Genre genre1 = null;

        @Builder.Default
        private Genre genre2 = null;

        @Builder.Default
        private Genre genre3 = null;

        private Boolean isOpen;

        private List<String> pictures;
    }

}
