package com.example.demo.domain.story.service;

import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;

import java.util.List;

public interface StoryService {
    
    // 특정 스토리 조회
    StoryResponseDto.StorySpecificResponseDto getStoryById(Long storyId) throws Exception;

    // 전체 스토리 리스트 조회
    StoryResponseDto.StoryListResponseDto getAllStoryList(int page, StoryRequestDto storyRequestDto);

    // 인기 스토리 조회
    List<StoryResponseDto.StoryThumbnailResponseDto> getPopularStories(int page);

    // 추천 스토리 조회
    List<StoryResponseDto.StoryThumbnailResponseDto> getRecommendStories(int page);

    // 최근 스토리 조회
    List<StoryResponseDto.StoryThumbnailResponseDto> getRecentStories(int page);

    // 추천 스토리 조회
    List<StoryResponseDto.MemberThumbnailResponseDto> getRecommendMembers(int page);
    
    // 스토리 저장
    void saveStory(StoryRequestDto storyRequestDto);

}
