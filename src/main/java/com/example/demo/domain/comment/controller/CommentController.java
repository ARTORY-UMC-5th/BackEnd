package com.example.demo.domain.comment.controller;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.domain.comment.service.CommentService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "댓글 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;


    @Operation(summary = "댓글 등록")
    @GetMapping("/{story-id}")
    public ResponseEntity<String> saveComment(@RequestBody CommentRequestDto commentRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {
        commentService.saveComment(commentRequestDto, memberInfoDto);
        return ResponseEntity.ok("comment saved successfully!");
    }

    @Operation(summary = "댓글 삭제")
    @GetMapping("/{story-id}")
    public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDto commentRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {
        commentService.deleteComment(commentRequestDto, memberInfoDto);
        return ResponseEntity.ok("comment deleted successfully!");
    }

}
