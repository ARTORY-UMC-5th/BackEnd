package com.example.demo.domain.myPage.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface MyPageService {


    public MyPageResponseDto.MemberGeneralResponseDto getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto);

    public void updateMemberInfo(@MemberInfo MemberInfoDto memberInfoDto, String userName, String introduction, String myKeyword, String nickname, String image);

    MyPageResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto);


    }


