package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.SubCommentRequestDto;
import com.example.demo.domain.comment.dto.SubCommentResponseDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.entity.SubComment;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.comment.repository.SubCommentRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.CommentException;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCommentServiceImpl implements SubCommentService{

    private final SubCommentRepository subCommentRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveSubcomment(SubCommentRequestDto.SubCommentSaveRequestDto subcommentSaveRequestDto, Long commentId, MemberInfoDto memberInfoDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_EXISTS));
        Member member = memberRepository.getById(memberInfoDto.getMemberId());

        SubComment subComment = SubComment.builder()
                .commentContext(subcommentSaveRequestDto.getCommentContext())
                .isDeleted(false)
                .comment(comment)
                .member(member)
                .build();

        subCommentRepository.save(subComment);
    }

    @Transactional
    public void deleteSubcomment(SubCommentRequestDto.SubCommentDeleteRequestDto subcommentDeleteRequestDto, Long commentId, MemberInfoDto memberInfoDto) {
        SubComment subComment = subCommentRepository.findById(subcommentDeleteRequestDto.getSubCommentId())
                .orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_EXISTS));
        Long memberId = memberInfoDto.getMemberId();

        if (Objects.equals(subComment.getMember().getMemberId(), memberId)) {
            subCommentRepository.delete(subComment);
        } else {
            throw new CommentException(ErrorCode.NOT_YOUR_COMMENT);
        }
    }

    @Transactional
    public void updateSubcomment(SubCommentRequestDto.SubCommentUpdateRequestDto subcommentUpdateRequestDto, Long commentId, MemberInfoDto memberInfoDto) {
        Member member = memberRepository.getById(memberInfoDto.getMemberId());
        SubComment subComment = subCommentRepository.findById(subcommentUpdateRequestDto.getSubCommentId())
                .orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_EXISTS));

        if (!Objects.equals(subComment.getMember().getMemberId(), member.getMemberId())) {
            throw new CommentException(ErrorCode.NOT_YOUR_COMMENT);
        }

        subComment = SubComment.builder()
                .id(subComment.getId())
                .commentContext(subcommentUpdateRequestDto.getCommentContext())
                .build();

        subCommentRepository.save(subComment);
    }

    @Override
    public List<SubCommentResponseDto> findByCommentId(Long commentId) {
        List<Object[]> objects = subCommentRepository.findByCommentId(commentId);

        List<SubCommentResponseDto> subCommentResponseDtoList = objects
                .stream()
                .map(array -> {
                    Long subCommentId = (Long) array[0];
                    String subCommentContext = (String) array[1];
                    Long memberId = (Long) array[2];
                    String memberNickname = (String) array[3];
                    return new SubCommentResponseDto(subCommentId, subCommentContext, memberId, memberNickname);
                })
                .collect(Collectors.toList());

        return subCommentResponseDtoList;
    }
}
