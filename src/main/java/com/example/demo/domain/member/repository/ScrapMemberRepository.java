package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.ScrapMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScrapMemberRepository extends JpaRepository<ScrapMember, Long> {

    @Query("SELECT 1 FROM ScrapMember s WHERE s.fromMember.memberId = :fromId AND EXISTS (SELECT 1 FROM Member m WHERE m.memberId = :toId)")
    boolean existsByfromMemberIdAndtoMemberId(Long fromId, Long toId);
}
