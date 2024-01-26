package com.example.demo.domain.comment.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {


    private String commentSatisfactionLevel;
    private String commentContext;
}
