package com.example.demo.web.kakaoToken.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "소셜 로그인", description = "서버 콜백 API (서버 내부용)")
@Controller
@RequiredArgsConstructor
public class SocialTokenController {


    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }



}
