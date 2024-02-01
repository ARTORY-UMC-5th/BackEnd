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
            "WHERE e.genreCategory1 = 'media' or e.genreCategory2 = 'media' or e.genreCategory3 = 'media' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findMediaExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'craft' or e.genreCategory2 = 'craft' or e.genreCategory3 = 'craft' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findCraftExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'design' or e.genreCategory2 = 'design' or e.genreCategory3 = 'design' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findDesignExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'picture' or e.genreCategory2 = 'picture' or e.genreCategory3 = 'picture' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findPictureExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'special_Exhibition' or e.genreCategory2 = 'special_Exhibition' or e.genreCategory3 = 'special_Exhibition' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findSpecialExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'sculpture' or e.genreCategory2 = 'sculpture' or e.genreCategory3 = 'sculpture' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findSculptureExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'plan_Exhibition' or e.genreCategory2 = 'plan_Exhibition' or e.genreCategory3 = 'plan_Exhibition' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findPlanExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'installation_Art' or e.genreCategory2 = 'installation_Art' or e.genreCategory3 = 'installation_Art' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findInstallationArtExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'painting' or e.genreCategory2 = 'painting' or e.genreCategory3 = 'painting' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findPaintingExhibitions(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT e, " +
            "le.isLiked, " +
            "se.isScrapped " +
            "FROM Exhibition e " +
            "LEFT JOIN LikeExhibition le ON e.id = le.exhibition.id AND le.member.memberId = :memberId " +
            "LEFT JOIN ScrapExhibition se ON e.id = se.exhibition.id AND se.member.memberId = :memberId " +
            "WHERE e.genreCategory1 = 'artist_Exhibition' or e.genreCategory2 = 'artist_Exhibition' or e.genreCategory3 = 'artist_Exhibition' " +
            " ORDER BY e.exhibitionDuration DESC")
    Page<Object[]> findArtistExhibitionExhibitions(@Param("memberId") Long memberId, Pageable pageable);


    @Query("select true " +
            "from ExhibitionGenre eg " +
            "where eg.exhibition.id = :exhibitionId ")
    Boolean existsByExhibitionId(Long exhibitionId);


    ExhibitionGenre getByExhibitionId(Long exhibitionId);
}