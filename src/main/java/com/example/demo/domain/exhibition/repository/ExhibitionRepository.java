package com.example.demo.domain.exhibition.repository;


import com.example.demo.domain.exhibition.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

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
    Page<Object[]> findActiveExhibitions(@Param("memberId") Long memberId,
                                         @Param("currentDate") LocalDate currentDate,
                                         Pageable pageable);



    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findAllByOrderByCreateTimeByDesc(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "ORDER BY e.exhibitionLikeCount DESC")
    Page<Object[]> findAllByOrderByExhibitionLikeCountDesc(@Param("memberId") Long memberId, Pageable pageable);

    //설명: 다 소문자로 변환 후 like를 사용해서 검색결과와 제목이 한 글자라도 일치한다면 해당 제목 전체 반환
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " + "WHERE LOWER(e.exhibitionTitle) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY e.exhibitionLikeCount DESC")
    Page<Object[]> findByExhibitionTitleContainingCase(@Param("memberId")Long memberId, String title, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +"ORDER BY RAND()")
    Page<Object[]> findRandomExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT le.isLiked FROM LikeExhibition le WHERE le.member.memberId = :memberId AND le.exhibition.id = :exhibitionId")
    Boolean findLikeStatusByMemberIdAndExhibitionId(@Param("memberId") Long memberId, @Param("exhibitionId") Long exhibitionId);

    @Query("SELECT se.isScrapped FROM ScrapExhibition se WHERE se.member.memberId = :memberId AND se.exhibition.id = :exhibitionId")
    Boolean findScrapStatusByMemberIdAndExhibitionId(@Param("memberId") Long memberId, @Param("exhibitionId") Long exhibitionId);




}