
package com.example.demo.domain.story.controller;

import com.example.demo.domain.comment.dto.CommentResponseDto;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.service.StoryService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
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

    private final StoryService storyService;


    @Operation(summary = "스토리 저장")
    @PostMapping("/save")
    public ResponseEntity<String> saveStory(@RequestBody StoryRequestDto.StoryRequestGeneralDto storyRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {
        try {
            storyService.saveStory(storyRequestDto,memberInfoDto);
            return ResponseEntity.ok("Story saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the story.");
        }
    }

    @Operation(summary = "스토리 수정")
    @PatchMapping("/upadte/{story-id}")
    public ResponseEntity<String> updateStory(@RequestBody StoryRequestDto.StoryRequestGeneralDto storyRequestDto, @RequestParam Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
        storyService.updateStory(storyRequestDto, storyId, memberInfoDto);
        return ResponseEntity.ok("story updated successfully!");

    }

    @Operation(summary = "특정 스토리 조회")
    @GetMapping("/{storyId}")
    public ResponseEntity<StoryResponseDto.StorySpecificResponseDto> getStory(@PathVariable Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
        StoryResponseDto.StorySpecificResponseDto story = storyService.getStoryById(storyId, memberInfoDto);
        return ResponseEntity.ok(story);
    }
    @Operation(summary = "특정 스토리 댓글조회")
    @GetMapping("/comment/{storyId}")
    public List<CommentResponseDto> getStoryComment(@PathVariable Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
        List<CommentResponseDto> commentListResponse = storyService.getCommentById(storyId, memberInfoDto);
        return commentListResponse;
    }

    @Operation(summary = "스토리 검색 조회")
    @GetMapping("/search")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getSearchStories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam String title,
            @MemberInfo MemberInfoDto memberInfoDto){
        List<StoryResponseDto.StoryThumbnailResponseDto> searchList = storyService.getSearchStoriesByTitle(page, title, memberInfoDto);

        return ResponseEntity.ok(searchList);
    }

    @Operation(summary = "추천 스토리 조회")
    @GetMapping("/recommend")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getRecommendStories(
            @RequestParam(defaultValue = "1") int page,
            @MemberInfo MemberInfoDto memberInfoDto) {

        List<StoryResponseDto.StoryThumbnailResponseDto> recommendStories = storyService.getRecommendStories(page, memberInfoDto);
        return ResponseEntity.ok(recommendStories);
    }

    @Operation(summary = "추천 유저 조회")
    @GetMapping("/recommend-member")
    public ResponseEntity<List<StoryResponseDto.MemberThumbnailResponseDto>> getRecommendMembers(
            @RequestParam(defaultValue = "1") int page,
            @MemberInfo MemberInfoDto memberInfoDto) {

        List<StoryResponseDto.MemberThumbnailResponseDto> recommendMembers = storyService.getRecommendMembers(page, memberInfoDto);
        return ResponseEntity.ok(recommendMembers);
    }

    @Operation(summary = "최근 스토리 조회")
    @GetMapping("/recent")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getRecentStories(
            @RequestParam(defaultValue = "1") int page,
            @MemberInfo MemberInfoDto memberInfoDto) {

        List<StoryResponseDto.StoryThumbnailResponseDto> recentStories = storyService.getRecentStories(page, memberInfoDto);
        return ResponseEntity.ok(recentStories);
    }

    @Operation(summary = "인기 스토리 조회")
    @GetMapping("/popularity")
    public ResponseEntity<List<StoryResponseDto.StoryThumbnailResponseDto>> getPopularityStories(
            @RequestParam(defaultValue = "1") int page,
            @MemberInfo MemberInfoDto memberInfoDto) {

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = storyService.getPopularStories(page, memberInfoDto);
        return ResponseEntity.ok(popularStories);
    }
    
    @Operation(summary = "모든 스토리 목록 조회")
    @GetMapping("/all")
    public ResponseEntity<StoryResponseDto.StoryListResponseDto> getAllStoryList(
            @RequestParam(defaultValue = "1") int page,
            @MemberInfo MemberInfoDto memberInfoDto) {

        StoryResponseDto.StoryListResponseDto storyList = storyService.getAllStoryList(page, memberInfoDto);
        return ResponseEntity.ok(storyList);
    }

}