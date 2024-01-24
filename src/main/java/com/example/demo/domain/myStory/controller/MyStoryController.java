package com.example.demo.domain.myStory.controller;

import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.domain.myStory.service.MyStoryServiceImpl;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "마이스토리 정보 관리", description = "마이스토리 정보 조회 및 수정하기 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mystory")
public class MyStoryController {

    private final MyStoryServiceImpl myStoryService;

//    @Operation(summary = "마이스토리에 내정보 조회", description = "마이스토리 창 들어갈때 호출되는 url")
//    @GetMapping("/info")
//    public ResponseEntity<MyStoryResponseDto.MemberGeneralResponseDto> getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {
//        MyStoryResponseDto.MemberGeneralResponseDto myStoryResponseDto = myStoryService.getMemberInfo(memberInfoDto);
//        return ResponseEntity.ok(myStoryResponseDto);
//    }
//
//    @Operation(summary = "스크랩한 전시회 목록 조회", description = "페이징 기능 포함")
//    @GetMapping("/scrapped")
//    public ResponseEntity<List<MyStoryResponseDto.ExhibitionGeneralResponseDto>> getScrappedExhibitions(
//            @MemberInfo MemberInfoDto memberInfoDto,
//            @RequestParam(defaultValue = "1") int page) {
//        List<MyStoryResponseDto.ExhibitionGeneralResponseDto> scrappedExhibitions = myStoryService.getScrappedExhibitionInfo(memberInfoDto, page);
//        return ResponseEntity.ok(scrappedExhibitions);
//    }

    @Operation(summary = "마이스토리에 내정보 및 스크랩한 전시회 목록 조회", description = "마이스토리 창 들어갈때 호출되는 url")
    @GetMapping("/all")
    public ResponseEntity<MyStoryResponseDto.MemberGeneralResponseDto> getAllMyStoryInfo(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        MyStoryResponseDto.MemberGeneralResponseDto myStoryResponseDto = myStoryService.getAllMyStoryInfo(memberInfoDto, page);
        return ResponseEntity.ok(myStoryResponseDto);
    }
}