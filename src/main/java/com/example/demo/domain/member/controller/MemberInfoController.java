package com.example.demo.domain.member.controller;


import com.example.demo.domain.member.dto.MemberInfoResponseDto;

import com.example.demo.domain.member.dto.MemberInfoSaveDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.service.MemberInfoService;
import com.example.demo.global.jwt.service.TokenManager;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberInfoService memberInfoService;
    private final MemberRepository memberRepository;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {

        Long memberId = memberInfoDto.getMemberId();
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(memberId);

        return ResponseEntity.ok(memberInfoResponseDto);
    }
    @GetMapping("/save")
    public ResponseEntity<Member> saveMemberInfo(@MemberInfo MemberInfoDto memberInfoDto, MemberInfoSaveDto memberInfoSaveDto) {

        Long memberId = memberInfoDto.getMemberId();
        Member member = memberInfoService.saveMemberInfo(memberInfoSaveDto , memberId);
        memberRepository.save(member);

        return ResponseEntity.ok(member);
    }

}
