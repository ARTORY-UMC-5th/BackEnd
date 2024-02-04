package com.example.demo.domain.story.service;

import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface ScrapStoryService {

    void scrapStory( @MemberInfo MemberInfoDto memberInfoDto, Long storyId);

    void unscrapStory(@MemberInfo MemberInfoDto memberInfoDto, Long storyId);
}