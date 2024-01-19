package com.example.demo.domain.exhibition.repository;


import com.example.demo.domain.exhibition.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    @Query("SELECT e FROM Exhibition e ORDER BY e.exhibitionStartDate ASC ")
    Page<Exhibition> findAllByOrderByCreateTimeByDesc(Pageable pageable);

    @Query("SELECT e FROM Exhibition e ORDER BY e.exhibitionLikeCount DESC")
    Page<Exhibition> findAllByOrderByExhibitionLikeCountDesc(Pageable pageable);

    @Query("SELECT e FROM Exhibition e ORDER BY RAND()")
    Page<Exhibition> findRandomExhibitions(Pageable pageable);


    //설명: 다 소문자로 변환 후 like를 사용해서 검색결과와 제목이 한 글자라도 일치한다면 해당 제목 전체 반환
    @Query("SELECT e FROM Exhibition e WHERE LOWER(e.exhibitionTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Exhibition> findByExhibitionTitleContainingCase(String title, Pageable pageable);

}