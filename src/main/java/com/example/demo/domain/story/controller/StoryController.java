package com.example.demo.domain.story.controller;

import com.example.demo.domain.story.converter.StoryConverter;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.service.StoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "스토리 정보 관리", description = "스토리 정보 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryServiceImpl storyService;
    private final StoryConverter storyConverter;


    @Operation(summary = "스토리 저장")
    @PostMapping("/save")
    public ResponseEntity<String> saveStory(@RequestBody StoryRequestDto storyRequestDto) {
        try {
            storyService.saveStory(storyRequestDto);
            return ResponseEntity.ok("Story saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the story.");
        }
    }


    @Operation(summary = "특정 스토리 조회")
    @GetMapping("/{storyId}")
    public ResponseEntity<StoryResponseDto.StorySpecificResponseDto> getStory(@PathVariable Long storyId) {
        StoryResponseDto.StorySpecificResponseDto story = storyService.getStoryById(storyId);
        return ResponseEntity.ok(story);
    }


    @Operation(summary = "모든 스토리 목록 조회")
    @GetMapping("")
    public ResponseEntity<StoryResponseDto.StoryListResponseDto> getAllStoryList(
            @RequestParam(defaultValue = "1") int page,
            @RequestBody(required = false) StoryRequestDto storyRequestDto) {

        StoryResponseDto.StoryListResponseDto storyList = storyService.getAllStoryList(page, storyRequestDto);
        return ResponseEntity.ok(storyList);
    }


    @Operation(summary = "인기 스토리 조회")
    @GetMapping("/popularity")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getPopularityStories(@RequestParam(defaultValue = "1") int page) {

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = storyService.getPopularStories(page);
        return ResponseEntity.ok(popularStories);
    }


    @Operation(summary = "추천 스토리 조회")
    @GetMapping("/recommend")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getRecommendStories(@RequestParam(defaultValue = "1") int page) {

        List<StoryResponseDto.StoryThumbnailResponseDto> recommendStories = storyService.getRecommendStories(page);
        return ResponseEntity.ok(recommendStories);
    }


    @Operation(summary = "최근 스토리 조회")
    @GetMapping("/recent")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getRecentStories(@RequestParam(defaultValue = "1") int page) {

        List<StoryResponseDto.StoryThumbnailResponseDto> recentStories = storyService.getRecentStories(page);
        return ResponseEntity.ok(recentStories);
    }


    @Operation(summary = "추천 유저 조회")
    @GetMapping("/recommend-member")
    public ResponseEntity<List<StoryResponseDto.MemberThumbnailResponseDto>> getRecommendMembers(@RequestParam(defaultValue = "1") int page) {

        List<StoryResponseDto.MemberThumbnailResponseDto> recommendMembers = storyService.getRecommendMembers(page);
        return ResponseEntity.ok(recommendMembers);
    }
}