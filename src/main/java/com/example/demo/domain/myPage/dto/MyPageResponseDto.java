package com.example.demo.domain.myPage.dto;

import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;



public class MyPageResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberGeneralResponseDto {
        private String introduction;
        private String myKeyword;
        private String nickname;
        private String image;
        @Setter
        private List<StoryGeneralResponseDto> stories;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoryGeneralResponseDto {
        private Long storyId;
        private String storyTitle;
        private String storyImage;

    }
}
