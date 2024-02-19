package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Member findByRefreshToken(String refreshToken);

    Member findByMemberId(Long memberid);


    @Query("SELECT distinct m " +
            "FROM Member m " +
            "LEFT JOIN ScrapMember sm ON sm.fromMember.memberId = :memberId " +
            "LEFT JOIN ScrapMember sm1 ON sm.toMember = sm1.fromMember " +
            "LEFT JOIN ScrapMember sm2 ON sm1.toMember = sm2.toMember and sm2.fromMember.memberId = :memberId " +
            "WHERE m = sm1.toMember and m.memberId != :memberId and (sm2 is null or sm2.isScrapped = false) " )
    Page<Member> recommendMember(Pageable pageable, @Param("memberId") Long memberId);

    @Query("select distinct m " +
            "from Member m " +
            "where m.memberId != :memberId " +
            "and not exists (select 1 from ScrapMember sm where sm.fromMember.memberId = :memberId and sm.toMember = m and sm.isScrapped = true) " +
            "order by rand()")
    Page<Member> initRecommendMember(Pageable pageable, Long memberId);
}
