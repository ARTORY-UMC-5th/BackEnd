package com.example.demo.domain.story.repository;

import com.example.demo.domain.story.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("select s from Story s order by s.storyLikeCount desc")
    Page<Story> findAllByOrderByStoryLikeCountDesc(Pageable pageable);

    @Query("select s from Story s order by s.creatTime desc")
    Page<Story> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
