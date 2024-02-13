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


    @Query("SELECT m, COALESCE(sm1.isScrapped, false) " +
            "FROM Member m " +
            "JOIN ScrapMember sm ON sm.fromMember.memberId = :memberId " +
            "JOIN ScrapMember sm1 ON sm.toMember.memberId = sm1.toMember.memberId " +
            "WHERE m.memberId = sm1.fromMember.memberId and m.memberId != :memberId " )
    Page<Object[]> recommendMember(Pageable pageable, Long memberId);
}
