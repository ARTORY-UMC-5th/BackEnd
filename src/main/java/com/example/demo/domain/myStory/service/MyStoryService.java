package com.example.demo.domain.myStory.service;


import com.example.demo.domain.myStory.dto.MyStoryRequestDto;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

import java.util.List;

public interface MyStoryService {

     List<MyStoryResponseDto.StorySpecificResponseDto> getSavedStories(@MemberInfo MemberInfoDto memberInfoDto, int year, int month, int day);

     MyStoryResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto, int page);
     List<MyStoryResponseDto.DateInfoExhibitionResponseDto> getExhibitionsByDate(MyStoryRequestDto.DateInfoRequestDto dateInfoRequestDto);

      void saveMemberMemo(@MemberInfo MemberInfoDto memberInfoDto, String memo) ;
     }


