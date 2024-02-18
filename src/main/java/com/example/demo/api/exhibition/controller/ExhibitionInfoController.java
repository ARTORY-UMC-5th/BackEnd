package com.example.demo.api.exhibition.controller;

import com.example.demo.api.exhibition.util.ExhibitionInfoUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전시회 정보 가져오기")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exhibition-info")
public class ExhibitionInfoController {

    private final ExhibitionInfoUtil exhibitionInfoUtil;

    @GetMapping("/get")
    public ResponseEntity<String> getExhibitionInfo() {
        exhibitionInfoUtil.getInfo();
        return ResponseEntity.ok("import exhibition Info successfully");
    }
}
