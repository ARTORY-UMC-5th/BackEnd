package com.example.demo.api.tokenTest;

import com.example.demo.domain.member.constant.Role;
import com.example.demo.global.jwt.dto.JwtTokenDto;
import com.example.demo.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token-test")
public class TokenTestController {

    private final TokenManager tokenManager;

    @GetMapping("/create")
    public JwtTokenDto createJwtTokenDto() {
        return tokenManager.createJwtTokenDto(1L, Role.ADMIN);
    }

    @GetMapping("/valid")
    public String validateJwtToken(@RequestParam String token) {
        tokenManager.validateToken(token);
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Long memberId = Long.valueOf((Integer) tokenClaims.get("memberId"));
        String role = (String) tokenClaims.get("role");
        log.info("memberId : {}", memberId);
        log.info("role : {}", role);
        return "success";
    }

}
