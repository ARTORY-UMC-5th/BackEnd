package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Member findByRefreshToken(String refreshToken);

    Member findByMemberId(Long memberid);


    @Query("SELECT distinct m " +
            "FROM Member m " +
            "JOIN ScrapMember sm ON sm.fromMember.memberId = :memberId " +
            "JOIN ScrapMember sm1 ON sm.toMember.memberId = sm1.fromMember.memberId " +
            "JOIN ScrapMember sm2 ON sm2.fromMember.memberId = :memberId and sm2.toMember = sm1.toMember " +
            "WHERE m = sm2.toMember and m.memberId != :memberId and sm2.isScrapped = false " )
    Page<Member> recommendMember(Pageable pageable, Long memberId);

    @Query("select distinct m " +
            "from Member m " +
            "join ScrapMember sm on sm.fromMember.memberId = :memberId " +
            "where m.memberId != :memberId and (sm is null or sm.isScrapped != true) " +
            "order by rand() ")
    Page<Member> initRecommendMember(Pageable pageable, Long memberId);
}
