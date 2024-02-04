package com.example.demo.domain.comment.controller;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.domain.comment.dto.SubCommentRequestDto;
import com.example.demo.domain.comment.service.CommentService;
import com.example.demo.domain.comment.service.SubCommentService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대댓글 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subcomments")
public class SubCommentController {

    private final SubCommentService subCommentService;


    @Operation(summary = "대댓글 등록")
    @PostMapping("/save/{comment-id}")
    public ResponseEntity<String> saveSubcomment(@RequestBody SubCommentRequestDto.SubCommentSaveRequestDto subcommentSaveRequestDto, @RequestParam Long commentId, @MemberInfo MemberInfoDto memberInfoDto) {
        subCommentService.saveSubcomment(subcommentSaveRequestDto, commentId, memberInfoDto);
        return ResponseEntity.ok("subcomment saved successfully!");
    }

    @Operation(summary = "대댓글 삭제")
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteSubcomment(@RequestParam Long subCommentId, @PathVariable Long commentId, @MemberInfo MemberInfoDto memberInfoDto) {
        SubCommentRequestDto.SubCommentDeleteRequestDto subcommentDeleteRequestDto = SubCommentRequestDto.SubCommentDeleteRequestDto.builder()
                .subCommentId(subCommentId)
                .build();
        subCommentService.deleteSubcomment(subcommentDeleteRequestDto, commentId, memberInfoDto);
        return ResponseEntity.ok("subcomment deleted successfully!");
    }

    @Operation(summary = "대댓글 수정")
    @PatchMapping("/update/{commentId}")
    public ResponseEntity<String> updateSubcomment(@RequestBody SubCommentRequestDto.SubCommentUpdateRequestDto subcommentUpdateRequestDto, @PathVariable Long commentId, @MemberInfo MemberInfoDto memberInfoDto){
        subCommentService.updateSubcomment(subcommentUpdateRequestDto, commentId, memberInfoDto);
        return ResponseEntity.ok("subcomment updated successfully!");
    }
}
