package com.example.demo.api.token.controller;

import com.example.demo.api.token.dto.AccessTokenResponseDto;
import com.example.demo.api.token.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "토큰 재발급", description = "header에 엑세스토큰 대신 -> refresh token O (Authorization : Bearer sdklfjas어쩌구)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenController {
    private final TokenService tokenService;
    @PostMapping("/access-token/issue")
    public ResponseEntity<AccessTokenResponseDto> createAccessToken(HttpServletRequest httpServletRequest){
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String refreshToken = authorizationHeader.split(" ")[1];
        AccessTokenResponseDto accessTokenResponseDto = tokenService.createAccessTokenByRefreshToken(refreshToken);
        return ResponseEntity.ok(accessTokenResponseDto);

    }
}
