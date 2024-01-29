package com.example.demo.domain.story.service;

import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

import java.util.List;

public interface StoryService {

    // 스토리 저장
    void saveStory(StoryRequestDto.StoryRequestGeneralDto storyRequestDto, @MemberInfo MemberInfoDto memberInfoDto);

    // 스토리 수정
    void updateStory(StoryRequestDto.StoryRequestGeneralDto storyRequestDto, Long storyId, MemberInfoDto memberInfoDto);

    // 특정 스토리 조회
    StoryResponseDto.StorySpecificResponseDto getStoryById(Long storyId,@MemberInfo MemberInfoDto memberInfoDto);

    // 스토리 검색 조회
    List<StoryResponseDto.StoryThumbnailResponseDto> getSearchStoriesByTitle(int page, String title, @MemberInfo MemberInfoDto memberInfoDto);

    // 추천 스토리 조회
    List<StoryResponseDto.StoryThumbnailResponseDto> getRecommendStories(int page, @MemberInfo MemberInfoDto memberInfoDto);

    // 추천 유저 조회
    List<StoryResponseDto.MemberThumbnailResponseDto> getRecommendMembers(int page,@MemberInfo MemberInfoDto memberInfoDto);

    // 최근 스토리 조회 (스토리 생성 순으로 조회 + 공개 스토리여야함)
    List<StoryResponseDto.StoryThumbnailResponseDto> getRecentStories(int page, @MemberInfo MemberInfoDto memberInfoDto);

    // 인기 스토리 조회
    List<StoryResponseDto.StoryThumbnailResponseDto> getPopularStories(int page, @MemberInfo MemberInfoDto memberInfoDto);

    // 전체 스토리 목록 조회
    StoryResponseDto.StoryListResponseDto getAllStoryList(int page, @MemberInfo MemberInfoDto memberInfoDto);


}