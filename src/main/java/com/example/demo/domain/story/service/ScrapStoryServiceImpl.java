package com.example.demo.domain.story.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.ScrapStoryRepository;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.StoryException;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapStoryServiceImpl implements ScrapStoryService{

    private final ScrapStoryRepository scrapStoryRepository;
    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;


    @Transactional
    public void scrapStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId) {
            Long memberId = memberInfoDto.getMemberId();

        Member member = memberRepository.getReferenceById(memberId);
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));

        ScrapStory temp = scrapStoryRepository.findByStoryIdAndMemberId(storyId, memberId);

        // 스크랩 로그가 없을 시 -> 데이터 저장 + isScrapped 값 true로 변경
        if (temp == null) {
            ScrapStory scrapStory = ScrapStory.builder()
                    .member(member)
                    .story(story)
                    .build();

            scrapStoryRepository.save(scrapStory);
            scrapStoryRepository.setIsScrappedTrue(scrapStory);

            // 스크랩 로그에서 isScrapped 가 false이거나 null일때 -> true로 변경
        } else if (temp.getIsScrapped() == null || temp.getIsScrapped() == false) {
            scrapStoryRepository.setIsScrappedTrue(temp);

            // isScrapped가 true 이면 Exception 발생
        } else {
            throw new StoryException(ErrorCode.SCRAP_EXISTS);
        }
    }


    @Transactional
    public void unscrapStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId) {
            Long memberId = memberInfoDto.getMemberId();

        storyRepository.findById(storyId).orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));

        ScrapStory scrapStory = scrapStoryRepository.findByStoryIdAndMemberId(storyId, memberId);

        if (scrapStory == null || scrapStory.getIsScrapped() == null || !scrapStory.getIsScrapped()) {
            throw new StoryException(ErrorCode.UNSCRAP_EXISTS);
        } else if (scrapStory.getIsScrapped()) {
            scrapStoryRepository.setIsLikedFalse(scrapStory);
        }

    }

}