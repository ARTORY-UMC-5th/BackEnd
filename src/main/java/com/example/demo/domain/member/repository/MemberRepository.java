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
//    Member findMemberByMemberId(Long id);

    //원재
    @Query("select m, sm.isScrapped " +
            "from Member m " +
            "left join ScrapMember sm on sm.fromMember.memberId = :memberId and m.memberId = sm.toMember.memberId ")
    Page<Object[]> recommendMemberWithScrapped(Pageable pageable, Long memberId);
}
