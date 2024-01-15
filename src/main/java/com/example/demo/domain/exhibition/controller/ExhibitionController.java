package com.example.demo.domain.exhibition.controller;


import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.service.ExhibitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exhibitions")
public class ExhibitionController {
    private ExhibitionService exhibitionService;

    @GetMapping("/recent")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionRecentResponseDto>> getRecentExhibitions(
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionRecentResponseDto> recentExhibitions = exhibitionService.getRecentExhibitions(page);
        return ResponseEntity.ok(recentExhibitions);
    }
}
