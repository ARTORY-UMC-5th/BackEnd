package com.example.demo.domain.story.controller;

import com.example.demo.domain.story.service.ScrapStoryService;
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

@Tag(name = "스토리 스크랩 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scrap-story")
public class ScrapStoryController {

    private final ScrapStoryService scrapStoryService;

    @Operation(summary = "스토리 스크랩", description = "param : memberId, storyId")
    @GetMapping("/story-scrapped")
    public ResponseEntity<String> scrapStory(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long storyId){

        scrapStoryService.scrapStory(memberInfoDto, storyId);
        return ResponseEntity.ok("success scrapped");
    }


    @Operation(summary = "스토리 스크랩 취소", description = "param : memberId, storyId")
    @GetMapping("/story-unscrapped")
    public ResponseEntity<String> unscrapStory(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long storyId){

        scrapStoryService.unscrapStory(memberInfoDto, storyId);
        return ResponseEntity.ok("success unscrapped");
    }
}