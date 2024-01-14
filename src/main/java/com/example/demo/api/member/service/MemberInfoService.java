package com.example.demo.api.member.service;


import com.example.demo.api.member.dto.MemberInfoResponseDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberService memberService;

    @Transactional(readOnly = true)
    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);
        return MemberInfoResponseDto.of(member);
    }

}
