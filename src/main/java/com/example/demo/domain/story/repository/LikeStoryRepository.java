package com.example.demo.domain.story.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.story.entity.LikeStory;
import com.example.demo.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeStoryRepository extends JpaRepository<LikeStory, Long> {

    // 좋아요를 했으므로 해당 스토리의 좋아요 수 1 증가
    @Modifying
    @Query("update Story s set s.storyLikeCount = s.storyLikeCount + 1 where s.id = :storyId")
    int updateLikeCount(Long storyId);

    // 좋아요를 취소했으므로 해당 스토리의 좋아요 수 1 감소
    @Modifying
    @Query("update Story s set s.storyLikeCount = s.storyLikeCount - 1 where s.id = :storyId")
    int updateUnlikeCount(Long storyId);


    LikeStory findByMemberAndStory(Member member, Story story);

    @Modifying
    @Query("update LikeStory ls " +
            "set ls.isLiked = true " +
            "where ls = :likeStory")
    void setIsLikedTrue(LikeStory likeStory);

    @Modifying
    @Query("update LikeStory ls " +
            "set ls.isLiked = false " +
            "where ls = :likeStory ")
    void setIsLikedFalse(LikeStory likeStory);


    @Query("select ls " +
            "from LikeStory ls " +
            "where ls.member.memberId = :memberId and ls.story.id = :storyId ")
    LikeStory findByMemberIdAndStoryId(Long memberId, Long storyId);
    void deleteByStoryId(Long storyId);
}