package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
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


    @Override
    public void saveComment(CommentRequestDto.CommentSaveRequestDto commentSaveRequestDto, Long storyId, MemberInfoDto memberInfoDto) {
        Member member = memberRepository.getById(memberInfoDto.getMemberId());
        Optional<Story> optionalStory = storyRepository.findById(storyId);

        if (optionalStory.isEmpty()) {
            throw new StoryException(ErrorCode.STORY_NOT_EXISTS);
        }

        Story story = optionalStory.get();

        // 댓글 생성
        Comment comment = Comment.builder()
                .story(story)
                .member(member)
                .commentContext(commentSaveRequestDto.getCommentContext())
                .commentSatisfactionLevel(commentSaveRequestDto.getCommentSatisfactionLevel())
                .build();

        commentRepository.save(comment);
    }


    @Override
    public void deleteComment(CommentRequestDto.CommentDeleteRequestDto commentDeleteRequestDto, Long storyId, MemberInfoDto memberInfoDto) {

        Comment comment = commentRepository.getById(commentDeleteRequestDto.getCommentId());
        Long commentMemberId = comment.getMember().getMemberId();


        //댓글 작성자와 삭제하려는 주체가 같으면 삭제 진행
        if (Objects.equals(commentMemberId, memberInfoDto.getMemberId())) {
            commentRepository.delete(comment);
        } else {
            throw new CommentException(ErrorCode.NOT_YOUR_COMMENT);
        }
    }


    @Override
    public void updateComment(CommentRequestDto.CommentUpdateRequestDto commentUpdateRequestDto, Long storyId, MemberInfoDto memberInfoDto) {

    }
}
