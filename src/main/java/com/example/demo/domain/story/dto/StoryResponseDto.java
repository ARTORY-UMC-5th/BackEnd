package com.example.demo.domain.story.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class StoryResponseDto {


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoryThumbnailResponseDto {
        private Long id;
        private String storyTitle;
        private String storyImage;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StorySpecificResponseDto {

        // 멤버 정보
        private Long memberId;
        private String memberNickname;
        private String memberProfile;
        private Boolean isScraped;

        // 전시회 정보
        private String exhibitionId;
        private String exhibitionTitle;
        private String exhibitionImage;

        // 스토리 정보
        private String storyTitle;
        private String storySatisfactionLevel;
        private String storyWeather;
        private String storyCompanion;
        private String storyGenre1;
        private String storyGenre2;
        private String storyGenre3;
        private String storyKeyword;
        private String storyContext;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberThumbnailResponseDto {
        private Long id;
        private String memberName;
        private String memberProfile;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoryListResponseDto {

        private List<StoryThumbnailResponseDto> recommendStoryDtoList;
        private List<StoryThumbnailResponseDto> poluarStoryDtoList;
        private List<StoryThumbnailResponseDto> recentStoryDtoList;
        private List<MemberThumbnailResponseDto> recommendMemberDtoList;
    }
}