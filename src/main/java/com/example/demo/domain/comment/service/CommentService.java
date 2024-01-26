package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface CommentService {
    void saveComment(CommentRequestDto commentRequestDto, MemberInfoDto memberInfoDto);

    void deleteComment(CommentRequestDto commentRequestDto, MemberInfoDto memberInfoDto);
}
