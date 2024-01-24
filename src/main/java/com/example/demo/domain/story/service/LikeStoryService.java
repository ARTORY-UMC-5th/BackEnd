package com.example.demo.domain.story.service;

public interface LikeStoryService {

    void likeStory(Long memberId, Long storyId);

    void unlikeStory(Long memberId, Long storyId);
}