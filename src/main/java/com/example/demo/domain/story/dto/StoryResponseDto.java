package com.example.demo.domain.story.dto;

import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.story.entity.Story;
import lombok.*;

import java.util.List;

public class StoryResponseDto {


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoryThumbnailResponseDto {
        private Long storyId;
        private String storyTitle;
        private String storyImage;
        private Boolean isLiked;
        private Boolean isScrapped;

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
        private Boolean isScrapped;

        // 전시회 정보
        private Long exhibitionId;
        private String exhibitionTitle;
        private String exhibitionImage;

        // 스토리 정보
        private Long storyId;
        private String storyTitle;
        private String storySatisfactionLevel;
        private String storyWeather;
        private String storyCompanion;
        private Genre storyGenre1;
        private Genre storyGenre2;
        private Genre storyGenre3;
        private String storyKeyword;
        private String storyContext;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberThumbnailResponseDto {
        private Long storyId;
        private String memberNickname;
        private String memberProfile;
        private Boolean isScrapped;
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


    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoryRepositoryResponseDto {

        private Story story;
        private Boolean isMemberScrapped;
    }
}