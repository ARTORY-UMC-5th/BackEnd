//package com.example.demo.domain.exhibition.repository;
//
//import com.example.demo.domain.exhibition.entity.LikeExhibition;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//
//public interface TestExhibitionRepository extends JpaRepository<LikeExhibition, Long> {
//    @Query("SELECT e FROM Exhibition e ORDER BY e.exhibitionDuration DESC")
//    Page<LikeExhibition> findAllByOrderByCreateTimeByDesc(Pageable pageable);
//}
