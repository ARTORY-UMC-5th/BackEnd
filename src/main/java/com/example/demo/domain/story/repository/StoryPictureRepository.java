package com.example.demo.domain.story.repository;

import com.example.demo.domain.story.entity.StoryPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryPictureRepository extends JpaRepository<StoryPicture, Long> {


    void deleteByStoryId(Long storyId);
}
