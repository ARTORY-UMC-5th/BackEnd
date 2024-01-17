package com.example.demo.domain.exhibition.controller;

import com.example.demo.domain.exhibition.service.ScrapExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scrap-exhibition")
@RequiredArgsConstructor

public class ScrapExhibitionController {

    private final ScrapExhibitionService scrapExhibitionService;


    @PostMapping("/exhibition-scrapped")
    public ResponseEntity<String> scrapExhibition(
            @RequestParam Long memberId,
            @RequestParam Long exhibitionId
    ) {
        scrapExhibitionService.scrapExhibition(memberId, exhibitionId);
        return ResponseEntity.ok("Exhibition scrapped successfully.");
    }
}