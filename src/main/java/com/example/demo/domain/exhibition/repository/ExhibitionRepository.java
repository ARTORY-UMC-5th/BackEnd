package com.example.demo.domain.exhibition.repository;

import com.example.demo.domain.exhibition.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    @Query("SELECT e FROM Exhibition e ORDER BY e.creatTimeBy DESC")
    List<Exhibition> findTopNByOrderByCreatTimeByDesc(int offset, int pageSize);
}




