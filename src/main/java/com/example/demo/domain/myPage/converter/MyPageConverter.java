package com.example.demo.domain.myPage.converter;


import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import org.springframework.stereotype.Component;


@Component
public class MyPageConverter {

    public MyPageResponseDto.MemberGeneralResponseDto convertToGeneralDto(Member member){
        MyPageResponseDto.MemberGeneralResponseDto dto = MyPageResponseDto.MemberGeneralResponseDto.builder()
                .image(member.getImage())
                .nickname(member.getNickname())
                .introduction(member.getIntroduction())
                .myKeyword(member.getMyKeyword())
                .build();
        return dto;
    }

    public MyPageResponseDto.StoryGeneralResponseDto convertToStoryDto(Story story){
        MyPageResponseDto.StoryGeneralResponseDto dto = MyPageResponseDto.StoryGeneralResponseDto.builder()
                .storyId(story.getId())
                .storyTitle(story.getStoryTitle())
                .storyImage(story.getStoryThumbnailImage())
                .build();
        return dto;
    }

}
