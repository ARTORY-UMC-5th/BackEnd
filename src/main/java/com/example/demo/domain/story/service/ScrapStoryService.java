package com.example.demo.domain.story.service;

import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface ScrapStoryService {

    void scrapStory(MemberInfoDto memberInfoDto, Long storyId);

    void unscrapStory(MemberInfoDto memberInfoDto, Long storyId);
}