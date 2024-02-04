package com.example.demo.domain.comment.service;


import com.example.demo.domain.comment.dto.SubCommentRequestDto;
import com.example.demo.domain.comment.dto.SubCommentResponseDto;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

import java.util.List;

public interface SubCommentService {

    void saveSubcomment(SubCommentRequestDto.SubCommentSaveRequestDto subcommentSaveRequestDto, Long commentId, MemberInfoDto memberInfoDto);

    void deleteSubcomment(SubCommentRequestDto.SubCommentDeleteRequestDto subcommentDeleteRequestDto, Long commentId, MemberInfoDto memberInfoDto);

    void updateSubcomment(SubCommentRequestDto.SubCommentUpdateRequestDto subcommentUpdateRequestDto, Long commentId, MemberInfoDto memberInfoDto);

    List<SubCommentResponseDto> findByCommentId(Long commentId);
}
