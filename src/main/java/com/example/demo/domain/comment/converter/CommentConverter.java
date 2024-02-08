package com.example.demo.domain.comment.converter;

import com.example.demo.domain.comment.dto.CommentResponseDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CommentResponseDto convertToResponseDto (Comment comment, Member member) {
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .commentId(comment.getId())
                .satisfactionLevel(comment.getCommentSatisfactionLevel())
                .commentContext(comment.getCommentContext())
                .memberId(member.getMemberId())
                .memberNickname(member.getNickname())
                .memberProfile(member.getImage())
                .build();

        return commentResponseDto;
    }
}
