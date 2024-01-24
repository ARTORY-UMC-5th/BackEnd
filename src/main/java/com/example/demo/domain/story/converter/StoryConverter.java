//package com.example.demo.domain.story.converter;
//
//
//import com.example.demo.domain.exhibition.entity.Exhibition;
//import com.example.demo.domain.member.entity.Member;
//import com.example.demo.domain.story.dto.StoryRequestDto;
//import com.example.demo.domain.story.entity.Story;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StoryConverter {
//    public Story convertToEntity(StoryRequestDto storyRequestDto, Member member, Exhibition exhibition) {
//        return Story.builder()
//                .storyTitle(storyRequestDto.getStoryTitle())
//                .storySatisfactionLevel(storyRequestDto.getStorySatisfactionLevel())
//                .storyWeather(storyRequestDto.getStoryWeather())
//                .storyCompanion(storyRequestDto.getStoryCompanion())
//                .storyKeyword(storyRequestDto.getStoryKeyword())
//                .storyViewingTime(storyRequestDto.getStoryViewingTime())
//                .storyContext(storyRequestDto.getStoryContext())
//                .genre1(storyRequestDto.getGenre1())
//                .genre2(storyRequestDto.getGenre2())
//                .genre3(storyRequestDto.getGenre3())
//                .isOpen(storyRequestDto.isOpen())
//                .member(member)
//                .exhibition(exhibition)
//                .build();
//    }
//}