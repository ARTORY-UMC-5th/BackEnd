package com.example.demo.domain.exhibition.controller;



import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.service.ExhibitionDistanceRecommendService;
import com.example.demo.domain.exhibition.service.ExhibitionService;
import com.example.demo.domain.member.service.MemberInfoService;
import com.example.demo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exhibitions")
@RequiredArgsConstructor

public class ExhibitionController {
    private final ExhibitionService exhibitionService;
    private final ExhibitionDistanceRecommendService exhibitionDistanceRecommendService;
    private final MemberService memberService;
    private final MemberInfoService memberInfoService;


    @PostMapping("/all")
    public ResponseEntity<ExhibitionResponseDto.ExhibitionListResponseDto> getAllExhibitionList(
            @RequestParam(defaultValue = "1") int page,
            @RequestBody(required = false) ExhibitionRequestDto requestDto) {
        ExhibitionResponseDto.ExhibitionListResponseDto allExhibitionList = exhibitionService.getAllExhibitionList(page, requestDto);
        return ResponseEntity.ok(allExhibitionList);
    }

    @PostMapping("/distanceRecommend")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> distanceRecommendExhibitionForUser(
            @RequestParam(defaultValue = "1") int page,
            @RequestBody(required = false) ExhibitionRequestDto requestDto) {

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> distanceRecommendExhibitions = exhibitionService.getDistanceRecommendExhibitions(requestDto, page);

        return ResponseEntity.ok(distanceRecommendExhibitions);
    }



    @GetMapping("/recent")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRecentExhibitions(
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions = exhibitionService.getRecentExhibitions(page);
        return ResponseEntity.ok(recentExhibitions);
    }

    @GetMapping("/popularity")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPopularityExhibitions(
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> popularityExhibitions = exhibitionService.getPopularityExhibitions(page);
        return ResponseEntity.ok(popularityExhibitions);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> searchExhibitionsByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchResults = exhibitionService.searchExhibitionsByTitle(title, page);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/{exhibitionId}")
    public ResponseEntity<ExhibitionResponseDto.ExhibitionSpecificResponseDto> getExhibitionById(
            @PathVariable Long exhibitionId) {
        ExhibitionResponseDto.ExhibitionSpecificResponseDto exhibition = exhibitionService.getExhibitionById(exhibitionId);
        return ResponseEntity.ok(exhibition);
    }

    @GetMapping("/random")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRandomExhibitions(
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> randomExhibitions = exhibitionService.getRandomExhibitions(page);
        return ResponseEntity.ok(randomExhibitions);
    }
    @GetMapping("/recommend")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getRecommendExhibitions(
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recommendExhibitions = exhibitionService.getRecommendExhibitions(page);
        return ResponseEntity.ok(recommendExhibitions);
    }

    @GetMapping("/similar")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getSimilarExhibitions(
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> similarExhibitions = exhibitionService.getSimilarExhibitions(page);
        return ResponseEntity.ok(similarExhibitions);
    }



}
