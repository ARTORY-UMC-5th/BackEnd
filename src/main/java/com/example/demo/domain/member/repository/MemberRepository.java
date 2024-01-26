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
    @Query("select fromMember, COALESCE(sm.isScrapped, false) " +
            "from ScrapMember sm " +
            "join Member fromMember on sm.fromMember.memberId = fromMember.memberId " +
            "join Member toMember on sm.toMember.memberId = toMember.memberId " +
            "where toMember.memberId = :memberId")
    Page<Object[]> recommendMember(Pageable pageable, Long memberId);
}
