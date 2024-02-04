package com.example.demo.domain.comment.controller;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.domain.comment.dto.CommentResponseDto;
import com.example.demo.domain.comment.service.CommentService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;


    @Operation(summary = "댓글 등록")
    @PostMapping("/save/{story-id}")
    public ResponseEntity<String> saveComment(@RequestBody CommentRequestDto.CommentSaveRequestDto commentSaveRequestDto, @RequestParam Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
        commentService.saveComment(commentSaveRequestDto, storyId, memberInfoDto);
        return ResponseEntity.ok("comment saved successfully!");
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/delete/{storyId}")
//    public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDto.CommentDeleteRequestDto commentDeleteRequestDto, @RequestParam Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
    public ResponseEntity<String> deleteComment(@PathVariable Long storyId,@RequestParam Long commentId, @MemberInfo MemberInfoDto memberInfoDto) {
        CommentRequestDto.CommentDeleteRequestDto commentDeleteRequestDto = CommentRequestDto.CommentDeleteRequestDto.builder()
                .commentId(commentId)
                .build();

        commentService.deleteComment(commentDeleteRequestDto, storyId, memberInfoDto);
        return ResponseEntity.ok("comment deleted successfully!");
    }

    @Operation(summary = "댓글 수정")
    @PatchMapping("/update/{storyId}")
    public ResponseEntity<String> updateComment(@RequestBody CommentRequestDto.CommentUpdateRequestDto commentUpdateRequestDto, @PathVariable Long storyId, @MemberInfo MemberInfoDto memberInfoDto){
        commentService.updateComment(commentUpdateRequestDto, storyId, memberInfoDto);
        return ResponseEntity.ok("comment updated successfully!");
    }
}
