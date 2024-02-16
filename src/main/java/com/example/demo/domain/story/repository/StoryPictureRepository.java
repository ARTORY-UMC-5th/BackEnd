package com.example.demo.domain.story.repository;

import com.example.demo.domain.story.entity.StoryPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryPictureRepository extends JpaRepository<StoryPicture, Long> {


    void deleteByStoryId(Long storyId);

    @Query("SELECT sp FROM StoryPicture sp JOIN sp.story s WHERE s.member.memberId = :memberId")
    List<StoryPicture> findAllByMemberId(@Param("memberId") Long memberId);
}
