package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.ScrapMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrapMemberRepository extends JpaRepository<ScrapMember, Long> {

    @Query("select sm " +
            "from ScrapMember sm " +
            "where sm.fromMember.memberId = :fromMemberId and sm.toMember.memberId = :toMemberId ")
    ScrapMember findByfromMemberIdAndtoMemberId(Long fromMemberId, Long toMemberId);

    @Modifying
    @Query("update ScrapMember sm " +
            "set sm.isScrapped = true " +
            "where sm = :scrapMember ")
    void setIsScrappedTrue(ScrapMember scrapMember);

    @Modifying
    @Query("update ScrapMember sm " +
            "set sm.isScrapped = false " +
            "where sm = :scrapMember ")
    void setIsScrappedFalse(ScrapMember scrapMember);
}