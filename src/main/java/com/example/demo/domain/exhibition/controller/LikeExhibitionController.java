package com.example.demo.domain.exhibition.controller;

import com.example.demo.domain.exhibition.service.LikeExhibitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전시회 좋아요", description = "전시회 좋아요 API")
@RestController
@RequestMapping("/api/like-exhibition")
@RequiredArgsConstructor

public class LikeExhibitionController {

    private final LikeExhibitionService likeExhibitionService;

    @Operation(summary = "전시회 좋아요", description = "memberId, exhibitionId 필요")
    @PostMapping("/exhibition-liked")
    public ResponseEntity<String> likeExhibition(
            @RequestParam Long memberId,
            @RequestParam Long exhibitionId
    ) {
        likeExhibitionService.likeExhibition(memberId, exhibitionId);
        return ResponseEntity.ok("Exhibition liked successfully.");
    }
}