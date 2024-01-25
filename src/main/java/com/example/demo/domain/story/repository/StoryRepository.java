package com.example.demo.domain.story.repository;

import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {


    // 스토리 엔티티 + 조회하는 멤버가 스토리를 생성한 멤버를 스크랩 했는지 여부 (isScrapped)
//    Optional<Story> findById(Long storyId);


    // 인기순 스토리 조회
    // story좋아요 순으로 조회 & 좋아요 수가 같으면 최신순으로 조회(임의)
    @Query("SELECT s, COALESCE(ls.isLiked, false), COALESCE(ss.isScrapped, false) " +
            "FROM Story s " +
            "LEFT JOIN LikeStory ls ON s.id = ls.story.id AND ls.member.memberId = :memberId " +
            "LEFT JOIN ScrapStory ss ON s.id = ss.story.id AND ss.member.memberId = :memberId " +
            "WHERE s.isOpen = true " +
            "ORDER BY s.storyLikeCount desc , s.creatTime DESC")
    Page<Object[]> findAllByOrderByStoryLikeCountDesc(Pageable pageable, @Param("memberId") Long memberId);


    // 최신순 스토리 조회
    // 스토리 생성순 + 공개처리한 스토리만 조회
    @Query("select s, COALESCE(ls.isLiked, false), COALESCE(ss.isScrapped, false) " +
            "from Story s " +
            "left join LikeStory ls on s.id = ls.story.id and ls.member.memberId = :memberId " +
            "left join ScrapStory ss on s.id = ss.story.id and ss.member.memberId = :memberId " +
            "where s.isOpen = true " +
            "order by s.updateTime desc")
    Page<Object[]> findAllByOrderByCreateTimeDesc(Pageable pageable, Long memberId);


    // 추천순 스토리 조회
    @Query("select s, COALESCE(ls.isLiked, false), COALESCE(ss.isScrapped, false) " +
            "from Story s " +
            "left join LikeStory ls on s.id = ls.story.id and ls.member.memberId = :memberId " +
            "left join ScrapStory ss on s.id = ss.story.id and ss.member.memberId = :memberId " +
            "left join Member m on m.memberId = :memberId " +
            "where s.isOpen = true and (s.genre1 = m.genre1 or s.genre1 = m.genre2 or s.genre1 = m.genre3 or s.genre2 = m.genre1 or s.genre2 = m.genre2 or s.genre2 = m.genre3 or s.genre3 = m.genre1 or s.genre3 = m.genre2 or s.genre3 = m.genre3) " +
            "order by s.storyLikeCount desc, s.creatTime desc, rand() ")
    Page<Object[]> findAllByRecommend(Pageable pageable, Long memberId);


    // 스토리 검색 조회
    // 검색한 결과 출력 (여러개 출력 시, 인기순으로 출력)
    @Query("select s, COALESCE(ls.isLiked, false), COALESCE(ss.isScrapped, false) " +
            "from Story s " +
            "left join LikeStory ls on s.id = ls.story.id and ls.member.memberId = :memberId " +
            "left join ScrapStory ss on s.id = ss.story.id and ss.member.memberId = :memberId " +
            "where s.storyTitle like %:title% " +
            "order by s.storyLikeCount desc")
    Page<Object[]> findByStoryTitleContaining(@Param("title") String title, Pageable pageable, Long memberId);
}