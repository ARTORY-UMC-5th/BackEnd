package com.example.demo.api.member.controller;


import com.example.demo.api.member.dto.MemberInfoResponseDto;
import com.example.demo.api.member.service.MemberInfoService;

import com.example.demo.global.jwt.service.TokenManager;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberInfoController {

    private final TokenManager tokenManager;
    private final MemberInfoService memberInfoService;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {

        Long memberId = memberInfoDto.getMemberId();
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(memberId);

        return ResponseEntity.ok(memberInfoResponseDto);

    }

}
