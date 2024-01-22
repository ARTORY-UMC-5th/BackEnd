package com.example.demo.domain.exhibition.controller;



import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.service.ExhibitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Tag(name = "전시 정보 관리", description = "전시 정보 조회, 추천, 검색 API")
@RestController
@RequestMapping("/api/exhibitions")
@RequiredArgsConstructor

public class ExhibitionController {
    private final ExhibitionService exhibitionService;
//
//    @Operation(summary = "모든 전시회 목록 조회", description = "페이징 및 검색 기능 포함")
//    @PostMapping("/all")
//    public ResponseEntity<ExhibitionResponseDto.ExhibitionListResponseDto> getAllExhibitionList(
//            @RequestParam Long memberId,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestBody(required = false) ExhibitionRequestDto requestDto) {
//        ExhibitionResponseDto.ExhibitionListResponseDto allExhibitionList = exhibitionService.getAllExhibitionList(memberId,page, requestDto);
//        return ResponseEntity.ok(allExhibitionList);
//    }

    @Operation(summary = "사용자에게 거리 기반 전시회 추천", description = "페이지와 검색 기능 포함")
    @PostMapping("/distanceRecommend")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> distanceRecommendExhibitionForUser(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page,
            @RequestBody(required = false) ExhibitionRequestDto requestDto) {

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> distanceRecommendExhibitions = exhibitionService.getDistanceRecommendExhibitions(requestDto,memberId, page);

        return ResponseEntity.ok(distanceRecommendExhibitions);
    }


    @Operation(summary = "최근 전시회 목록 조회", description = "페이징 기능 포함")
    @GetMapping("/recent")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRecentExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "2024-01-22", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate,
            @RequestParam(defaultValue = "1") int page) {

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions = exhibitionService.getRecentExhibitions(memberId, currentDate, page);
        return ResponseEntity.ok(recentExhibitions);
    }

    @Operation(summary = "인기 전시회 목록 조회", description = "페이징 기능 포함")
    @GetMapping("/popularity")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPopularityExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> popularityExhibitions = exhibitionService.getPopularityExhibitions(memberId, page);
        return ResponseEntity.ok(popularityExhibitions);
    }

    @Operation(summary = "전시회 목록 검색", description = "단어 검색하면 해당 단어 포함되는 제목 가진 전시회 검색, 페이징 기능 포함")
    @GetMapping("/search")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> searchExhibitionsByTitle(
            @RequestParam String title,
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchResults = exhibitionService.searchExhibitionsByTitle(title, memberId, page);
        return ResponseEntity.ok(searchResults);
    }

    @Operation(summary = "특정 전시회 조회", description = "특정 전시회 선택시 전시회 정보 조회")
    @GetMapping("/{exhibitionId}")
    public ResponseEntity<ExhibitionResponseDto.ExhibitionSpecificResponseDto> getExhibitionById(
            @PathVariable Long exhibitionId) {
        ExhibitionResponseDto.ExhibitionSpecificResponseDto exhibition = exhibitionService.getExhibitionById(exhibitionId);
        return ResponseEntity.ok(exhibition);
    }
    @Operation(summary = "랜덤 전시회 목록 조회", description = "페이징 기능 포함")
    @GetMapping("/random")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRandomExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> randomExhibitions = exhibitionService.getRandomExhibitions(memberId, page);
        return ResponseEntity.ok(randomExhibitions);
    }


//    @Operation(summary = "추천 전시회 목록 조회", description = "페이징 기능 포함")
//    @GetMapping("/recommend")
//    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRecommendExhibitions(
//            @RequestParam(defaultValue = "1") int page) {
//        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recommendExhibitions = exhibitionService.getRecommendExhibitions(page);
//        return ResponseEntity.ok(recommendExhibitions);
//    }
//    @Operation(summary = "유사한 전시회 목록 조회", description = "페이징 기능 포함")
//    @GetMapping("/similar")
//    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getSimilarExhibitions(
//            @RequestParam(defaultValue = "1") int page) {
//        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> similarExhibitions = exhibitionService.getSimilarExhibitions(page);
//        return ResponseEntity.ok(similarExhibitions);
//    }
//
//
//
}
