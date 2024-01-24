package com.example.demo.domain.story.repository;

import com.example.demo.domain.story.entity.ScrapStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapStoryRepository extends JpaRepository<ScrapStory, Long> {

    @Modifying
    @Query("delete from ScrapStory ss where ss.member.memberId = :memberId and ss.story.id = :storyId")
    void deleteByIds(Long memberId, Long storyId);


    @Query("select ss " +
            "from ScrapStory ss " +
            "where ss.member.memberId = :memberId and ss.story.id = :storyId ")
    ScrapStory findByStoryIdAndMemberId(Long storyId, Long memberId);


    @Modifying
    @Query("update ScrapStory ss " +
            "set ss.isScrapped = false " +
            "where ss = :scrapStory")
    void setIsLikedFalse(ScrapStory scrapStory);

    @Modifying
    @Query("update ScrapStory ss " +
            "set ss.isScrapped = true " +
            "where ss = :scrapStory")
    void setIsScrappedTrue(ScrapStory scrapStory);
}