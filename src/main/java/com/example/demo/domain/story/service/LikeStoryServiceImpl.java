package com.example.demo.domain.story.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.entity.LikeStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.LikeStoryRepository;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.StoryException;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.InvalidRelationIdException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeStoryServiceImpl implements LikeStoryService{

    private final LikeStoryRepository likeStoryRepository;
    private final StoryRepository storyRepository;
    private final MemberRepository memberRepository;


    // memberId, storyId에 해당하는 데이터를 생성 + 해당 스토리에 좋아요 카운트 ++
    @Transactional
    public void likeStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId) {
        Long memberId = memberInfoDto.getMemberId();

        Member member = memberRepository.getReferenceById(memberId);
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));


        // 스토리가 비공개처리면 좋아요 기능 x
        if (!story.getIsOpen()) {
            throw new StoryException(ErrorCode.STORY_PRIVATE);
        }

        LikeStory temp = likeStoryRepository.findByMemberAndStory(member, story);

        // 좋아요 로그가 없을 시 -> 데이터 저장 + isLiked 값 true로 변경
        if (temp == null) {
            LikeStory likeStory = LikeStory.builder()
                    .story(story)
                    .member(member)
                    .build();

            likeStoryRepository.save(likeStory);
            likeStoryRepository.setIsLikedTrue(likeStory);

        } else if (temp.getIsLiked() == null || temp.getIsLiked() == false) {
            likeStoryRepository.setIsLikedTrue(temp);

        } else {
            throw new StoryException(ErrorCode.LIKE_EXISTS);
        }

        // 좋아요 수 1 증가
        likeStoryRepository.updateLikeCount(story.getId());
    }


    // memberId, storyId에 해당하는 데이터의 isLiked를 false로 변경 + 해당 스토리에 좋아요 카운트 --
    @Transactional
    public void unlikeStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId) {
        Long memberId = memberInfoDto.getMemberId();

        Story story = storyRepository.findById(storyId).orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));

        LikeStory likeStory = likeStoryRepository.findByMemberIdAndStoryId(memberId, storyId);

        if (likeStory  == null || likeStory.getIsLiked() == null || !likeStory.getIsLiked()) {
            throw new StoryException(ErrorCode.UNLIKE_EXISTS);


            // 스토리가 비공개면 좋아요 기능 x
        } else if (!story.getIsOpen()) {
            throw new StoryException(ErrorCode.STORY_PRIVATE);


            // true일때 -> false로 변경 & 좋아요 수 1 감소
        } else if (likeStory.getIsLiked()) {
            likeStoryRepository.setIsLikedFalse(likeStory);
            likeStoryRepository.updateUnlikeCount(storyId);
        }
    }
}