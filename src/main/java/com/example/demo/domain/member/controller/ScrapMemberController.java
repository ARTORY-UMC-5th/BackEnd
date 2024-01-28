package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.service.ScrapMemberService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "멤버 스크랩 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scrap-member")
public class ScrapMemberController {

    private final ScrapMemberService scrapMemberService;

    @Operation(summary = "멤버 스크랩")
    @PostMapping("/member-scrapped")
    public ResponseEntity<String> scrapMember(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long toMemberId) {

        scrapMemberService.scrapMember(memberInfoDto, toMemberId);
        return ResponseEntity.ok("success scrapped");
    }


    @Operation(summary = "멤버 스크랩 취소")
    @PostMapping("/member-unscrapped")
    public ResponseEntity<String> unscrapMember(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long toMemberId) {

        scrapMemberService.unscrapMember(memberInfoDto, toMemberId);
        return ResponseEntity.ok("success unscrapped");
    }
}
