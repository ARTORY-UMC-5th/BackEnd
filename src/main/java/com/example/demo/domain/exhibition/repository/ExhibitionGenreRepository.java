package com.example.demo.domain.exhibition.repository;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ExhibitionGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExhibitionGenreRepository extends JpaRepository<ExhibitionGenre, Long> {

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'media' OR e.genreCategory2 = 'media' OR e.genreCategory3 = 'media') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'media' THEN 0 " +
            "WHEN e.genreCategory2 = 'media' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findMediaExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'craft' OR e.genreCategory2 = 'craft' OR e.genreCategory3 = 'craft') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'craft' THEN 0 " +
            "WHEN e.genreCategory2 = 'craft' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findCraftExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'design' OR e.genreCategory2 = 'design' OR e.genreCategory3 = 'design') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'design' THEN 0 " +
            "WHEN e.genreCategory2 = 'design' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findDesignExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'picture' OR e.genreCategory2 = 'picture' OR e.genreCategory3 = 'picture') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'picture' THEN 0 " +
            "WHEN e.genreCategory2 = 'picture' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findPictureExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'special_exhibition' OR e.genreCategory2 = 'special_exhibition' OR e.genreCategory3 = 'special_exhibition') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'special_exhibition' THEN 0 " +
            "WHEN e.genreCategory2 = 'special_exhibition' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findSpecialExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'sculpture' OR e.genreCategory2 = 'sculpture' OR e.genreCategory3 = 'sculpture') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'sculpture' THEN 0 " +
            "WHEN e.genreCategory2 = 'sculpture' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findSculptureExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'plan_exhibition' OR e.genreCategory2 = 'plan_exhibition' OR e.genreCategory3 = 'plan_exhibition') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'plan_exhibition' THEN 0 " +
            "WHEN e.genreCategory2 = 'plan_exhibition' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findPlanExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'installation_art' OR e.genreCategory2 = 'installation_art' OR e.genreCategory3 = 'installation_art') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'installation_art' THEN 0 " +
            "WHEN e.genreCategory2 = 'installation_art' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findInstallationArtExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'painting' OR e.genreCategory2 = 'painting' OR e.genreCategory3 = 'painting') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'painting' THEN 0 " +
            "WHEN e.genreCategory2 = 'painting' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findPaintingExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE (e.genreCategory1 = 'artist_exhibition' OR e.genreCategory2 = 'artist_exhibition' OR e.genreCategory3 = 'artist_exhibition') " +
            "ORDER BY CASE " +
            "WHEN e.genreCategory1 = 'artist_exhibition' THEN 0 " +
            "WHEN e.genreCategory2 = 'artist_exhibition' THEN 1 " +
            "ELSE 2 " +
            "END, e.exhibitionDuration DESC")
    Page<Object[]> findArtistExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);


    @Query("select true " +
            "from ExhibitionGenre eg " +
            "where eg.exhibition.id = :exhibitionId ")
    Boolean existsByExhibitionId(Long exhibitionId);


    ExhibitionGenre getByExhibitionId(Long exhibitionId);
}