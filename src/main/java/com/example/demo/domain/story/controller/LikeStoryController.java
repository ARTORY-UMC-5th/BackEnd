package com.example.demo.domain.story.controller;

import com.example.demo.domain.story.service.LikeStoryService;
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

@Tag(name = "스토리 좋아요 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like-story")
public class LikeStoryController {

    private final LikeStoryService likeStoryService;

    @Operation(summary = "스토리 좋아요", description = "param : memberId, storyId")
    @GetMapping("/story-liked")
    public ResponseEntity<String> likeStory(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long storyId){

        likeStoryService.likeStory(memberInfoDto, storyId);
        return ResponseEntity.ok("success liked");
    }


    @Operation(summary = "스토리 좋아요 취소", description = "param memberId, storyId")
    @GetMapping("/story-unliked")
    public ResponseEntity<String> unlikeStory(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long storyId){

        likeStoryService.unlikeStory(memberInfoDto, storyId);
        return ResponseEntity.ok("success unliked");
    }
}