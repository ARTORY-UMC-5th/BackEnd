package com.example.demo.domain.myPage.dto;

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
        private List<MyStoryResponseDto> stories;

        @Setter
        private List<MyAlbumResponseDto> storyPictures;

        @Setter
        private List<ScrappedStoryResponseDto> scrappedStories;


        @Setter
        private List<ScrappedMemberResponseDto> scrappedMembers;


    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyStoryResponseDto {
        private Long storyId;
        private String storyTitle;
        private String storyImage;


    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyAlbumResponseDto {
        private Long storyPictureId;
        private String pictureUrl;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrappedStoryResponseDto {
        private Long storyId;
        private String storyImage;
        private Boolean isScrapped;
        @Setter
        private Boolean isLiked;


    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScrappedMemberResponseDto {
        private Long memberId;
        private String profile;
        private Boolean isScrapped;
        private Boolean isLiked;


    }
}
