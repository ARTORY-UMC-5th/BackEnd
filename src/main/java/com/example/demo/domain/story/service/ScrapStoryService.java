package com.example.demo.domain.story.service;

public interface ScrapStoryService {

    void scrapStory(Long memberId, Long storyId);

    void unscrapStory(Long memberId, Long storyId);
}