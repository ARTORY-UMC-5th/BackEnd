package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Member findByRefreshToken(String refreshToken);

    Member findByMemberId(Long memberid);


    @Query("SELECT distinct m " +
            "FROM Member m " +
            "JOIN ScrapMember sm ON sm.fromMember.memberId = :memberId " +
            "JOIN ScrapMember sm1 ON sm.toMember.memberId = sm1.fromMember.memberId " +
            "JOIN ScrapMember sm2 ON m = sm2.fromMember and sm2.toMember = sm1.toMember " +
            "WHERE m.memberId = sm1.toMember.memberId and m.memberId != :memberId and sm2.isScrapped = false " )
    Page<Member> recommendMember(Pageable pageable, Long memberId);

    @Query("select distinct m " +
            "from Member m " +
            "left join ScrapMember sm on sm.toMember = m and sm.fromMember.memberId = :memberId " +
            "where m.memberId != :memberId and sm.isScrapped = false " +
            "order by rand() ")
    Page<Member> initRecommendMember(Pageable pageable, Long memberId);
}
