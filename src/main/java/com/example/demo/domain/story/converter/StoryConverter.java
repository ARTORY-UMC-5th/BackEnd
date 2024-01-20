package com.example.demo.domain.story.converter;


import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryConverter {

    public Story convertToEntity(StoryRequestDto storyRequestDto, Member member, Exhibition exhibition) {
        return Story.builder()
                .storyTitle(storyRequestDto.getStoryTitle())
                .storySatisfactionLevel(storyRequestDto.getStorySatisfactionLevel())
                .storyWeather(storyRequestDto.getStoryWeather())
                .storyCompanion(storyRequestDto.getStoryCompanion())
                .storyKeyword(storyRequestDto.getStoryKeyword())
                .storyViewingTime(storyRequestDto.getStoryViewingTime())
                .storyContext(storyRequestDto.getStoryContext())
                .genre1(storyRequestDto.getGenre1())
                .genre2(storyRequestDto.getGenre2())
                .genre3(storyRequestDto.getGenre3())
                .isOpen(storyRequestDto.isOpen())
                .member(member)
                .exhibition(exhibition)
                .build();
    }

    public StoryResponseDto.StorySpecificResponseDto convertToSpecificResponseDto(Story story){
        Member member = story.getMember();
        Exhibition exhibition = story.getExhibition();

        StoryResponseDto.StorySpecificResponseDto storySpecificResponseDto = StoryResponseDto.StorySpecificResponseDto.builder()
                .memberId(member.getMemberId())
                .memberNickname(member.getNickname())
                .memberProfile(member.getProfile())
                .isScraped(member.isScraped)

                .exhibitionId(exhibition.getId())
                .exhibitionImage(exhibition.getExhibitionImage())
                .exhibitionTitle(exhibition.getExhibitionTitle())

                .storyTitle(story.getStoryTitle())
                .storySatisfactionLevel(story.getStorySatisfactionLevel())
                .storyWeather(story.getStoryWeather())
                .storyCompanion(story.getStoryCompanion())
                .storyGenre1(story.getGenre1())
                .storyGenre2(story.getGenre2())
                .storyGenre3(story.getGenre3())
                .storyKeyword(story.getStoryKeyword())
                .storyContext(story.getStoryContext())
                .build();

        return  storySpecificResponseDto;
    }


    public static StoryResponseDto.StoryThumbnailResponseDto convertToThumbnailResponseDto(Story story){

        StoryResponseDto.StoryThumbnailResponseDto storyThumbnailResponseDto = StoryResponseDto.StoryThumbnailResponseDto.builder()
                .id(story.getId())
                .storyTitle(story.getStoryTitle())
                .storyImage(story.getStoryThumbnailImage())
                .build();

        return storyThumbnailResponseDto;
    }
}