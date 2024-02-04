package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.BusinessException;
import com.example.demo.global.error.exception.CommentException;
import com.example.demo.global.error.exception.StoryException;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{


    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;


    @Transactional
    public void saveComment(CommentRequestDto.CommentSaveRequestDto commentSaveRequestDto, Long storyId, MemberInfoDto memberInfoDto) {
        Member member = memberRepository.findById(memberInfoDto.getMemberId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));

        // 댓글 생성
        Comment comment = Comment.builder()
                .story(story)
                .member(member)
                .commentContext(commentSaveRequestDto.getCommentContext())
                .commentSatisfactionLevel(commentSaveRequestDto.getCommentSatisfactionLevel())
                .build();

        // 필수 값 안넣었을 시 예외는 추후 작성
        commentRepository.save(comment);
    }


    @Transactional
    public void deleteComment(CommentRequestDto.CommentDeleteRequestDto commentDeleteRequestDto, Long storyId, MemberInfoDto memberInfoDto) {

        Comment tempComment = commentRepository.findById(commentDeleteRequestDto.getCommentId())
                .orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_EXISTS));
        Long commentMemberId = tempComment.getMember().getMemberId();


        //댓글 작성자와 삭제하려는 주체가 같으면 삭제 진행 <- 취소
        // 대댓글이 존재하므로 댓글이 삭제될 시 isDeleted가 true가 되는걸로 변경
        if (Objects.equals(commentMemberId, memberInfoDto.getMemberId())) {
            Comment comment = tempComment.toBuilder()
                    .isDeleted(true)
                    .build();

            commentRepository.save(comment);
//            commentRepository.delete(comment);
        } else {
            throw new CommentException(ErrorCode.NOT_YOUR_COMMENT);
        }
    }


    @Transactional
    public void updateComment(CommentRequestDto.CommentUpdateRequestDto commentUpdateRequestDto, Long storyId, MemberInfoDto memberInfoDto) {
        Member member = memberRepository.findById(memberInfoDto.getMemberId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));
        Long commentId = commentUpdateRequestDto.getCommentId();


        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_EXISTS));

        if (!Objects.equals(member.getMemberId(), comment.getMember().getMemberId())) {
            throw new CommentException(ErrorCode.NOT_YOUR_COMMENT);
        }

        comment = Comment.builder()
                .id(commentId)
                .story(story)
                .member(member)
                .commentContext(commentUpdateRequestDto.getCommentContext())
                .commentSatisfactionLevel(commentUpdateRequestDto.getCommentSatisfactionLevel())
                .build();

        // 필수 값 안넣었을 시 예외는 추후 작성

        commentRepository.save(comment);
    }
}
