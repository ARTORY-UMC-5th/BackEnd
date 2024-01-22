package com.example.demo.domain.exhibition.repository;

import com.example.demo.domain.exhibition.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ExhibitionGenreRepository extends JpaRepository<Exhibition, Long> {

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'media' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findMediaExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'craft' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findCraftExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'design' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findDesignExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'picture' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findPictureExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'specialExhibition' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findSpecialExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'sculpture' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findSculptureExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'planExhibition' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findPlanExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'installationArt' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findInstallationArtExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'painting' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findPaintingExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory = 'artistExhibition' ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findArtistExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);


}
