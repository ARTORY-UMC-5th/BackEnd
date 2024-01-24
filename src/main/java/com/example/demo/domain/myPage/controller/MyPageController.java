package com.example.demo.domain.myPage.controller;

import com.example.demo.domain.myPage.dto.MyPageRequestDto;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.myPage.service.MyPageService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "마이페이지 정보 관리", description = "마이페이지 정보 조회 및 수정하기 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;


    @Operation(summary = "마이페이지 정보 조회", description = "마이페이지 창 들어갈때 호출되는 url")
    @GetMapping("/info")
    public ResponseEntity<MyPageResponseDto> getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {
        MyPageResponseDto mypageResponseDto = myPageService.getMemberInfo(memberInfoDto);
        return ResponseEntity.ok(mypageResponseDto);
    }



    @Operation(summary = "마이페이지 정보 수정하기", description = "수정하기 버튼 누르면 이게 호출되어서 update")
    @PostMapping("/update")
    public ResponseEntity<Void> updateMemberInfo(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestBody MyPageRequestDto myPageRequestDto
    ) {
        myPageService.updateMemberInfo(memberInfoDto,
                myPageRequestDto.getIntroduction(),
                myPageRequestDto.getMyKeyword(),
                myPageRequestDto.getNickname(),
                myPageRequestDto.getImage());
        return ResponseEntity.ok().build();
    }
}