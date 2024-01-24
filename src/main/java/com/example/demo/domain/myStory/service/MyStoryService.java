package com.example.demo.domain.myStory.service;


import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface MyStoryService {


     MyStoryResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto, int page);

}


