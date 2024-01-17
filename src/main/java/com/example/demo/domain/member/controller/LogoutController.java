package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.service.LogoutService;
import com.example.demo.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 정보 조회, 수정", description = "사용자 정보 API - 수정하기 전에 전체 정보를 불러와서 채워주세요. 기존 데이터에 덮어 쓰기 위함")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

        String accessToken = authorizationHeader.split(" ")[1];
        logoutService.logout(accessToken);

        return ResponseEntity.ok("logout success");
    }

}
