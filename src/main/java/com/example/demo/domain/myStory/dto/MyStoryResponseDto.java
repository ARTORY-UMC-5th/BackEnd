package com.example.demo.domain.myStory.dto;

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
        private String memo;
        @Setter
        private List<ExhibitionGeneralResponseDto> exhibitions;

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
}