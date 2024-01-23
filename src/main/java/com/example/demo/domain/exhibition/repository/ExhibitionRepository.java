package com.example.demo.domain.exhibition.repository;


import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.constant.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {


    //최신순
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +
            "AND CAST(e.exhibitionStartDate AS LocalDate) <= :currentDate " +
            "ORDER BY ABS(DATEDIFF(CURRENT_DATE, CAST(e.exhibitionStartDate AS LocalDate))), e.exhibitionStartDate ASC")
    Page<Object[]> findAllByOrderByCreateTimeByDesc(@Param("memberId") Long memberId,
                                         @Param("currentDate") LocalDate currentDate,
                                         Pageable pageable);



    //좋아요순
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +
            "ORDER BY e.exhibitionLikeCount DESC")
    Page<Object[]> findAllByOrderByExhibitionLikeCountDesc(@Param("memberId") Long memberId, Pageable pageable);


    //찾기
    //설명: 다 소문자로 변환 후 like를 사용해서 검색결과와 제목이 한 글자라도 일치한다면 해당 제목 전체 반환
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +
            "AND LOWER(e.exhibitionTitle) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY e.exhibitionStartDate DESC")

    Page<Object[]> findByExhibitionTitleContainingCase(@Param("memberId")Long memberId, String title, Pageable pageable);


    //랜덤
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +"ORDER BY RAND()")
    Page<Object[]> findRandomExhibitions(@Param("memberId") Long memberId, Pageable pageable);


    //장르에 따른 가중치로 정렬하고, 그 다음에 생성 시간을 기준으로 정렬
    @Query("SELECT e, le.isLiked, se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +
            "AND (e.genreCategory = :genre1 OR e.genreCategory = :genre2 OR e.genreCategory = :genre3) " +
            "ORDER BY CASE " +
            "  WHEN e.genreCategory = :genre1 THEN 1 " +
            "  WHEN e.genreCategory = :genre2 THEN 2 " +
            "  WHEN e.genreCategory = :genre3 THEN 3 " +
            "  ELSE 4 END, e.creatTime DESC")
    Page<Object[]> findRecommendedExhibitions(@Param("memberId") Long memberId,
                                              @Param("genre1") String genre1,
                                              @Param("genre2") String genre2,
                                              @Param("genre3") String genre3,
                                              Pageable pageable);


    @Query("SELECT le.isLiked FROM LikeExhibition le WHERE le.member.memberId = :memberId AND le.exhibition.id = :exhibitionId")
    Boolean findLikeStatusByMemberIdAndExhibitionId(@Param("memberId") Long memberId, @Param("exhibitionId") Long exhibitionId);

    @Query("SELECT se.isScrapped FROM ScrapExhibition se WHERE se.member.memberId = :memberId AND se.exhibition.id = :exhibitionId")
    Boolean findScrapStatusByMemberIdAndExhibitionId(@Param("memberId") Long memberId, @Param("exhibitionId") Long exhibitionId);



}

