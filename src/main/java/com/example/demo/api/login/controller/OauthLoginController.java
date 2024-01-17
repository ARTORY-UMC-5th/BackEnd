package com.example.demo.api.login.controller;

import com.example.demo.api.login.dto.OauthLoginDto;
import com.example.demo.api.login.service.OauthLoginService;
import com.example.demo.api.login.validator.OauthValidator;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.global.util.AuthorizationHeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/oauth")
//public class OauthLoginController {
//
//    private final OauthValidator oauthValidator;
//    private final OauthLoginService oauthLoginService;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto,
//                                                             HttpServletRequest httpServletRequest) {
//
//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
//        oauthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());
//
//        String accessToken = authorizationHeader.split(" ")[1];
//        OauthLoginDto.Response jwtTokenResponseDto = oauthLoginService.oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));
//
//        System.out.println("kako-login");
//
//        return ResponseEntity.ok(jwtTokenResponseDto);
//    }
//
//
//
//}
