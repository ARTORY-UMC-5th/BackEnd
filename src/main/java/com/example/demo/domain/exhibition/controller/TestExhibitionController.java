//package com.example.demo.domain.exhibition.controller;
//
//import com.example.demo.domain.exhibition.dto.TestExhibitionResponseDto;
//import com.example.demo.domain.exhibition.service.TestExhibitionService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Tag(name = "전시 정보 관리", description = "전시 정보 조회, 추천, 검색 API")
//@RestController
//@RequestMapping("/api/testexhibitions")
//@RequiredArgsConstructor
//
//public class TestExhibitionController {
//    private final TestExhibitionService testExhibitionService;
//
//    @Operation(summary = "최근 전시회 목록 조회", description = "페이징 기능 포함")
//    @GetMapping("/recent")
//    public ResponseEntity<List<TestExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRecentExhibitions(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam Long memberId) {
//        List<TestExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions =
//                testExhibitionService.getRecentExhibitions(page, memberId);
//        return ResponseEntity.ok(recentExhibitions);
//    }
//}