package com.example.demo.domain.story.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.converter.StoryConverter;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{

    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final StoryRepository storyRepository;
    private final StoryConverter storyConverter;


    @Override
    public StoryResponseDto.StorySpecificResponseDto getStoryById(Long storyId) {
        Story story = storyRepository.findById(storyId).orElseThrow();

        return storyConverter.convertToSpecificResponseDto(story);
    }


    @Override
    public StoryResponseDto.StoryListResponseDto getAllStoryList(int page, StoryRequestDto storyRequestDto) {

        StoryResponseDto.StoryListResponseDto allStoryDto = StoryResponseDto.StoryListResponseDto.builder()
                .poluarStoryDtoList(getPopularStories(page))
                .recommendStoryDtoList(getRecommendStories(page))
                .recentStoryDtoList(getRecentStories(page))
                .recommendMemberDtoList(getRecommendMembers(page))
                .build();

        return allStoryDto;
    }


    @Override
    public List<StoryResponseDto.StoryThumbnailResponseDto> getPopularStories(int page) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Story> popularStoryPage = storyRepository.findAllByOrderByStoryLikeCountDesc(pageable);

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = popularStoryPage.getContent()
                .stream()
                .map(StoryConverter::convertToThumbnailResponseDto)
                .collect(Collectors.toList());

        return popularStories;
    }


    @Override
    public List<StoryResponseDto.StoryThumbnailResponseDto> getRecommendStories(int page) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        // 추천 알고리즘은 추후 구현
        Page<Story> popularStoryPage = storyRepository.findAllByOrderByStoryLikeCountDesc(pageable);

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = popularStoryPage.getContent()
                .stream()
                .map(StoryConverter::convertToThumbnailResponseDto)
                .collect(Collectors.toList());

        return popularStories;
    }


    @Override
    public List<StoryResponseDto.StoryThumbnailResponseDto> getRecentStories(int page) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Story> popularStoryPage = storyRepository.findAllByOrderByCreateTimeDesc(pageable);

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = popularStoryPage.getContent()
                .stream()
                .map(StoryConverter::convertToThumbnailResponseDto)
                .collect(Collectors.toList());

        return popularStories;
    }


    @Override
    public List<StoryResponseDto.MemberThumbnailResponseDto> getRecommendMembers(int page) {
        // 추후 구현
        return null;
    }


    @Transactional
    public void saveStory(StoryRequestDto storyRequestDto) {
        Long memberId = storyRequestDto.getMemberId();
        Long exhibitionId = storyRequestDto.getExhibitionId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EXHIBITION_NOT_EXISTS));

        Story story = storyConverter.convertToEntity(storyRequestDto, member, exhibition);
        storyRepository.save(story);
    }


}
