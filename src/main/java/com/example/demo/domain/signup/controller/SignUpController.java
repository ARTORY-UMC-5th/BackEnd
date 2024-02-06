//package com.example.demo.domain.signup.controller;
//
//
//import com.example.demo.domain.signup.dto.SignUpRequestDto;
//import com.example.demo.domain.signup.service.SignUpService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "폼 로그인")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/signup")
//public class SignUpController {
//    private final SignUpService signUpService;
//    @Operation(summary = "회원가입")
//    @PostMapping("/form-login")
//    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
//        signUpService.signUp(signUpRequestDto);
//        return ResponseEntity.ok().build();
//    }
//}