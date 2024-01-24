package com.example.demo.domain.myStory.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyStoryResponseDto {
    private String nickname;
    private String image;
    @Lob
    private String memo;



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