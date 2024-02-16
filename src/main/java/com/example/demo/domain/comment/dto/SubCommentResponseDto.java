package com.example.demo.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCommentResponseDto {

    // 대댓글
    private Long subCommentId;
    private String subCommentContext;

    //멤버
    private Long memberId;
    private String memberProfile;
    private String memberNickname;
}