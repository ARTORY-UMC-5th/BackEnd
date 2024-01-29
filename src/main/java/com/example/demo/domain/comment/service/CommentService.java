package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface CommentService {

    void saveComment(CommentRequestDto.CommentSaveRequestDto commentSaveRequestDto, Long storyId, @MemberInfo MemberInfoDto memberInfoDto);

    void deleteComment(CommentRequestDto.CommentDeleteRequestDto commentDeleteRequestDto, Long storyId, @MemberInfo MemberInfoDto memberInfoDto);

    void updateComment(CommentRequestDto.CommentUpdateRequestDto commentUpdateRequestDto, Long storyId, @MemberInfo MemberInfoDto memberInfoDto);
}
