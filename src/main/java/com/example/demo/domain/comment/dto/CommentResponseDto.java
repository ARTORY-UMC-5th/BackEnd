package com.example.demo.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    // 댓글
    private Long commentId;
    private String satisfactionLevel;
    private String commentContext;

    // 멤버
    private Long memberId;
    private String memberProfile;
    private String memberNickname;

}
