package com.example.demo.domain.myStory.dto;
import com.example.demo.domain.story.constant.State;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;


public class MyStoryResponseDto {



    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberGeneralResponseDto {
        private String nickname;
        private String image;
        @Lob
        @Column(length = 1000000)
        private String memo;
        @Setter
        private List<ExhibitionGeneralResponseDto> exhibitions;
        @Setter
        private List<StoryGeneralResponseDto> stories;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoryGeneralResponseDto {
        private int year;
        private int month;
        private int day;
        private Long StoryId;
        private String exhibitionTitle;
        private State storyState;

    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StorySpecificResponseDto {

        private Long StoryId;
        private State storyState;
        private Long exhibitionId;
        private String exhibitionImage;
        private String exhibitionTitle;
    }

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
    public static class DateInfoExhibitionResponseDto {
        private Long ExhibitionId;
        private String exhibitionTitle;
        private String exhibitionImage;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateAndTitleRequestDto {
        private int year;
        private int month;
        private int day;
        private String exhibitionTitle;

    }
}