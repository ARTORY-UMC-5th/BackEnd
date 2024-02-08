package com.example.demo.domain.FormLogin.controller;

import com.example.demo.domain.FormLogin.dto.FormLoginRequestDto;
import com.example.demo.domain.FormLogin.service.FormLoginService;
import com.example.demo.global.jwt.dto.JwtTokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "폼 로그인 & 회원가입", description = "폼 로그인 & 회원가입 API")
@RestController
@RequestMapping("/api/form")
@RequiredArgsConstructor
public class FormLoginController {

    private final FormLoginService formLoginService;

    @Operation(summary = "폼 회원가입", description = "항상 memberType = FORM,role = USER 넣으셔야 합니다.")
    @PostMapping("/signUp")
    public ResponseEntity<JwtTokenDto> signup(@RequestBody FormLoginRequestDto.SignUpRequestDto signupRequest) {
        JwtTokenDto jwtTokenDto = formLoginService.signUp(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtTokenDto);
    }

    @Operation(summary = "폼 로그인")
    @PostMapping("/signIn")
    public ResponseEntity<JwtTokenDto> formLogin(@RequestBody FormLoginRequestDto.SignInRequestDto loginRequest) {
        JwtTokenDto jwtTokenDto = formLoginService.signIn(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(jwtTokenDto);
    }
}

