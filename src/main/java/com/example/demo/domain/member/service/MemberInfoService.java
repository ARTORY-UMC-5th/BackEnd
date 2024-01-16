package com.example.demo.domain.member.service;


import com.example.demo.domain.member.dto.MemberInfoResponseDto;
import com.example.demo.domain.member.dto.MemberInfoSaveDto;
import com.example.demo.domain.member.entity.Member;
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

    public Member saveMemberInfo(MemberInfoSaveDto memberInfoSaveDto, Long memberId){
        Member member = memberService.findMemberByMemberId(memberId);
        return member.toBuilder()
                .memberId(member.getMemberId())
                .age(member.getAge())
                .nickname(member.getNickname())
                .image(member.getImage())
                .gender(member.getGender())
                .genre1(member.getGenre1())
                .genre2(member.getGenre2())
                .genre3(member.getGenre3())
                .memberName(member.getMemberName())
                .profile(member.getProfile())
                .build();
    }






}

