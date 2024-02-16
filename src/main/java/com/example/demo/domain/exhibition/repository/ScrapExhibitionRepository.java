package com.example.demo.domain.exhibition.repository;


import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrapExhibitionRepository extends JpaRepository<ScrapExhibition, Long> {

    // 특정 맴버와 전시회에 대한 ScrapExhibition을 찾기 위한 메서드
    Optional<ScrapExhibition> findByMemberAndExhibition(Member member, Exhibition exhibition);
}