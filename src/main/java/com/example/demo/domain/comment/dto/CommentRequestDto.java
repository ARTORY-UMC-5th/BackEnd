package com.example.demo.domain.comment.dto;

import lombok.*;

public class CommentRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentSaveRequestDto {

        private String commentSatisfactionLevel;
        private String commentContext;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentUpdateRequestDto {

        private Long commentId;
        private String commentSatisfactionLevel;
        private String commentContext;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDeleteRequestDto {

        private Long commentId;

    }
}
