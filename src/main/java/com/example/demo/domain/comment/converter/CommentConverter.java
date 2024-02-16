package com.example.demo.domain.comment.converter;

import com.example.demo.domain.comment.dto.CommentResponseDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CommentResponseDto convertToResponseDto (Comment comment) {
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .commentId(comment.getId())
                .satisfactionLevel(comment.getCommentSatisfactionLevel())
                .commentContext(comment.getCommentContext())
                .memberId(comment.getMember().getMemberId())
                .memberNickname(comment.getMember().getNickname())
                .memberProfile(comment.getMember().getImage())
                .build();

        return commentResponseDto;
    }
}