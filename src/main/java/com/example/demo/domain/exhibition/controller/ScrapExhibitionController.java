package com.example.demo.domain.exhibition.controller;

import com.example.demo.domain.exhibition.service.ScrapExhibitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전시회 스크랩", description = "전시회 스크랩 API")
@RestController
@RequestMapping("/api/scrap-exhibition")
@RequiredArgsConstructor

public class ScrapExhibitionController {

    private final ScrapExhibitionService scrapExhibitionService;

    @Operation(summary = "전시회 스크랩", description = "memberId, exhibitionId 필요")
    @PostMapping("/exhibition-scrapped")
    public ResponseEntity<String> scrapExhibition(
            @RequestParam Long memberId,
            @RequestParam Long exhibitionId
    ) {
        scrapExhibitionService.scrapExhibition(memberId, exhibitionId);
        return ResponseEntity.ok("Exhibition scrapped successfully.");
    }
    @Operation(summary = "전시회 스크랩 취소", description = "memberId, exhibitionId 필요")
    @PostMapping("/exhibition-disScrapped")
    public ResponseEntity<String> disScrapExhibition(
            @RequestParam Long memberId,
            @RequestParam Long exhibitionId
    ) {
        scrapExhibitionService.disScrapExhibition(memberId, exhibitionId);
        return ResponseEntity.ok("Exhibition disScrapped successfully.");
    }

}