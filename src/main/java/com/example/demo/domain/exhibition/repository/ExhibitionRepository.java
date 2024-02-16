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


    //스크랩한거 전체(myStory 위한 것)
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM ScrapExhibition se " +
            "LEFT JOIN Exhibition e ON e.id = se.exhibition.id " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "WHERE se.isScrapped = true AND se.member.memberId = :memberId AND e.isEnded = false AND e.isStarted = true " +
            "ORDER BY se.updateTime DESC")
    Page<Object[]> findAllByOrderByUpdateTimeExhibition(@Param("memberId") Long memberId, Pageable pageable);

    //최신순
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
//            "AND e.isStarted = true " +
            "AND CAST(e.exhibitionStartDate AS LocalDate) <= :currentDate " +
            "ORDER BY ABS(DATEDIFF(CURRENT_DATE, CAST(e.exhibitionStartDate AS LocalDate))), e.exhibitionStartDate ASC")
    Page<Object[]> findAllByOrderByStartDateByDesc(@Param("memberId") Long memberId,
                                                   @Param("currentDate") LocalDate currentDate,
                                                   Pageable pageable);


    //임박순
    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +
            "AND CAST(e.exhibitionEndDate AS LocalDate) >= :currentDate " +
            "ORDER BY ABS(DATEDIFF(CURRENT_DATE, CAST(e.exhibitionEndDate AS LocalDate))), e.exhibitionEndDate DESC")
    Page<Object[]> findAllByOrderByEndDateByDesc(@Param("memberId") Long memberId,
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

    //랜덤한개(메인용)
    @Query("SELECT e " +
            "FROM Exhibition e " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Exhibition findRandomOneExhibition();


    //장르에 따른 가중치로 정렬하고, 그 다음에 생성 시간을 기준으로 정렬
    @Query("SELECT e, le.isLiked, se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.isEnded = false " +
            "AND e.isStarted = true " +
            "AND (e.genreCategory1 = :genre1 OR e.genreCategory2 = :genre2 OR e.genreCategory3 = :genre3) " +
            "ORDER BY CASE " +
            "  WHEN e.genreCategory1 = :genre1 THEN 1 " +
            "  WHEN e.genreCategory2 = :genre2 THEN 2 " +
            "  WHEN e.genreCategory3 = :genre3 THEN 3 " +
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



    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'media' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findMediaExhibition();



    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'craft' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")

    Exhibition findCraftExhibition();


    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'design'ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")

    Exhibition findDesignExhibition();


@Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'picture' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findPictureExhibition();


    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'special_exhibition' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findSpecialExhibitionExhibition();



    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'sculpture' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findSculptureExhibition();


    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'plan_exhibition' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findPlanExhibitionExhibition();



    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'installation_art' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findInstallationArtExhibition();


    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'painting' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")
    Exhibition findPaintingExhibition();

    @Query("SELECT e FROM Exhibition e WHERE e.genreCategory1 = 'artist_Exhibition' ORDER BY ABS(FUNCTION('DATEDIFF', CAST(e.exhibitionStartDate AS java.time.LocalDate), CURRENT_DATE())) ASC LIMIT 1")

    Exhibition findArtistExhibitionExhibition();


}
