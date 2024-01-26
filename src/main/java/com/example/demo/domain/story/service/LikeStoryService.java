package com.example.demo.domain.story.service;

import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface LikeStoryService {

    void likeStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId);

    void unlikeStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId);
}