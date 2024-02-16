package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.service.LogoutService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import com.example.demo.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 정보 조회, 수정", description = "사용자 정보 API - 수정하기 전에 전체 정보를 불러와서 채워주세요. 기존 데이터에 덮어 쓰기 위함")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class LogoutController {

    private final LogoutService logoutService;

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@MemberInfo MemberInfoDto memberInfoDto) {

        Long memberId = memberInfoDto.getMemberId();
        logoutService.logout(memberId);

        return ResponseEntity.ok("logout success");
    }

}
