package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface CommentService {

    void saveComment(CommentRequestDto.CommentSaveRequestDto commentSaveRequestDto, Long storyId, MemberInfoDto memberInfoDto);

    void deleteComment(CommentRequestDto.CommentDeleteRequestDto commentDeleteRequestDto, Long storyId, MemberInfoDto memberInfoDto);

    void updateComment(CommentRequestDto.CommentUpdateRequestDto commentUpdateRequestDto, Long storyId, MemberInfoDto memberInfoDto);
}
