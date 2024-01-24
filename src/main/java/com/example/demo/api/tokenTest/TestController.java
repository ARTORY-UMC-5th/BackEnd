package com.example.demo.api.tokenTest;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
class TokenTestController {


    @GetMapping("/upload")
    public String createJwtTokenDto() {
        return "fileUpload";
    }



}
