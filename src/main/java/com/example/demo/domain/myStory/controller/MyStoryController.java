package com.example.demo.domain.myStory.controller;

import com.example.demo.domain.myStory.dto.MyStoryRequestDto;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.domain.myStory.service.MyStoryServiceImpl;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.service.StoryServiceImpl;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "마이스토리 정보 관리", description = "마이스토리 정보 조회 및 수정하기 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mystory")
public class MyStoryController {

    private final MyStoryServiceImpl myStoryService;
    private final StoryServiceImpl storyService;

    @Operation(summary = "스토리 저장")
    @PostMapping("/save")
    public ResponseEntity<String> saveStory(@RequestBody StoryRequestDto.StoryRequestDateDto storyRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {
        try {
            storyService.saveStoryNotDate(storyRequestDto,memberInfoDto);
            return ResponseEntity.ok("Story saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the story.");
        }
    }
    @Operation(summary = "마이스토리에 내정보 및 스크랩한 전시회 목록 조회", description = "마이스토리 창 들어갈때 호출되는 url, exhibitions에는 스크랩한 전시회 중 날짜가 유효(시작했고 끝나지 않은)한것만 나옴")
    @GetMapping("/all")
    public ResponseEntity<MyStoryResponseDto.MemberGeneralResponseDto> getAllMyStoryInfo(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        MyStoryResponseDto.MemberGeneralResponseDto myStoryResponseDto = myStoryService.getAllMyStoryInfo(memberInfoDto, page);
        return ResponseEntity.ok(myStoryResponseDto);
    }

    @Operation(summary = "멤버 메모 저장", description = "로그인한 멤버의를 저장합니다.")
    @PostMapping("/saveMemo")
    public ResponseEntity<String> saveMemberMemo(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam String memo) {
        myStoryService.saveMemberMemo(memberInfoDto, memo);
        return ResponseEntity.ok("멤버 메모가 성공적으로 저장되었습니다.");
    }

    @Operation(summary = "전시회 저장한 날짜에 따른 전시회 목록 조회", description = "전시회를 저장한 날짜에 따른 전시회 목록을 조회합니다.")
    @GetMapping("/bySavedDate")
    public ResponseEntity<List<MyStoryResponseDto.StorySpecificResponseDto>> getMySaveStoryInfo(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {
        List<MyStoryResponseDto.StorySpecificResponseDto> myStoryResponseDtos = myStoryService.getSavedStories(memberInfoDto, year, month, day);
        return ResponseEntity.ok(myStoryResponseDtos);
    }

    @Operation(summary = "저장할 전시회 목록 검색", description = "단어 검색하면 해당 단어 포함되고 해당 날짜 전시회 중 검색")
    @GetMapping("/search")
    public ResponseEntity<List<MyStoryResponseDto.DateInfoExhibitionResponseDto>> searchExhibitionsByTitle(
            @RequestParam String title,
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {
        List<MyStoryResponseDto.DateInfoExhibitionResponseDto> searchResults = myStoryService.getDateAndTitleRequestDto(
                memberInfoDto,
                MyStoryRequestDto.DateAndTitleRequestDto.builder()
                        .year(year)
                        .month(month)
                        .day(day)
                        .build(),
                title);

        List<MyStoryResponseDto.DateInfoExhibitionResponseDto> filteredResults = searchResults.stream()
                .filter(exhibition -> exhibition.getExhibitionTitle().contains(title))
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredResults);
    }





}