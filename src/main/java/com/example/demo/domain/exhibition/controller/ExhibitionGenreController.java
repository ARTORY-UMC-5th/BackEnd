package com.example.demo.domain.exhibition.controller;

import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.repository.ExhibitionGenreRepository;
import com.example.demo.domain.exhibition.service.ExhibitionGenreService;
import com.example.demo.domain.exhibition.service.ExhibitionGenreServiceImpl;
import com.example.demo.domain.exhibition.service.ExhibitionService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "전시회 장르", description = "전시회 장르별 API")
@RestController
@RequestMapping("/api/cagegory")
@RequiredArgsConstructor

public class ExhibitionGenreController {
    private final ExhibitionGenreService exhibitionGenreService;


    @Operation(summary = " media 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/media")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getMediaExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> mediaExhibitions = exhibitionGenreService.getMediaExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(mediaExhibitions);
    }

    @Operation(summary = " craft 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/craft")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getCraftExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> craftExhibitions = exhibitionGenreService.getCraftExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(craftExhibitions);
    }

    @Operation(summary = " design 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/design")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getDesignExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> designExhibitions = exhibitionGenreService.getDesignExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(designExhibitions);
    }

    @Operation(summary = " picture 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/picture")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPictureExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> pictureExhibitions = exhibitionGenreService.getPictureExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(pictureExhibitions);
    }

    @Operation(summary = " specialExhibition 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/specialExhibition")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getSpecialExhibitionExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> specialExhibitionExhibitions = exhibitionGenreService.getSpecialExhibitionExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(specialExhibitionExhibitions);
    }

    @Operation(summary = " sculpture 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/sculpture")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getSculptureExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> sculptureExhibitions = exhibitionGenreService.getSculptureExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(sculptureExhibitions);
    }

    @Operation(summary = " planExhibition 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/planExhibition")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPlanExhibitionExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> planExhibitionExhibitions = exhibitionGenreService.getPlanExhibitionExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(planExhibitionExhibitions);
    }

    @Operation(summary = " installationArt 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/installationArt")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getInstallationArtExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> installationArtExhibitions = exhibitionGenreService.getInstallationArtExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(installationArtExhibitions);
    }

    @Operation(summary = " painting 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/painting")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPaintingExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> paintingExhibitions = exhibitionGenreService.getPaintingExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(paintingExhibitions);
    }

    @Operation(summary = " artistExhibition 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/artistExhibition")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getArtistExhibitionExhibitions(
            @MemberInfo MemberInfoDto memberInfoDto,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> artistExhibitionExhibitions = exhibitionGenreService.getArtistExhibitionExhibitions(memberInfoDto, page);
        return ResponseEntity.ok(artistExhibitionExhibitions);
    }




}