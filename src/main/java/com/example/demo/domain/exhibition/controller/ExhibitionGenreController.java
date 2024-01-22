package com.example.demo.domain.exhibition.controller;

import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.repository.ExhibitionGenreRepository;
import com.example.demo.domain.exhibition.service.ExhibitionGenreService;
import com.example.demo.domain.exhibition.service.ExhibitionGenreServiceImpl;
import com.example.demo.domain.exhibition.service.ExhibitionService;
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
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> mediaExhibitions = exhibitionGenreService.getMediaExhibitions(memberId, page);
        return ResponseEntity.ok(mediaExhibitions);
    }

    @Operation(summary = " craft 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/craft")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getCraftExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> craftExhibitions = exhibitionGenreService.getCraftExhibitions(memberId, page);
        return ResponseEntity.ok(craftExhibitions);
    }

    @Operation(summary = " design 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/design")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getDesignExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> designExhibitions = exhibitionGenreService.getDesignExhibitions(memberId, page);
        return ResponseEntity.ok(designExhibitions);
    }

    @Operation(summary = " picture 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/picture")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPictureExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> pictureExhibitions = exhibitionGenreService.getPictureExhibitions(memberId, page);
        return ResponseEntity.ok(pictureExhibitions);
    }

    @Operation(summary = " specialExhibition 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/specialExhibition")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getSpecialExhibitionExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> specialExhibitionExhibitions = exhibitionGenreService.getSpecialExhibitionExhibitions(memberId, page);
        return ResponseEntity.ok(specialExhibitionExhibitions);
    }

    @Operation(summary = " sculpture 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/sculpture")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getSculptureExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> sculptureExhibitions = exhibitionGenreService.getSculptureExhibitions(memberId, page);
        return ResponseEntity.ok(sculptureExhibitions);
    }

    @Operation(summary = " planExhibition 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/planExhibition")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPlanExhibitionExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> planExhibitionExhibitions = exhibitionGenreService.getPlanExhibitionExhibitions(memberId, page);
        return ResponseEntity.ok(planExhibitionExhibitions);
    }

    @Operation(summary = " installationArt 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/installationArt")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getInstallationArtExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> installationArtExhibitions = exhibitionGenreService.getInstallationArtExhibitions(memberId, page);
        return ResponseEntity.ok(installationArtExhibitions);
    }

    @Operation(summary = " painting 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/painting")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getPaintingExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> paintingExhibitions = exhibitionGenreService.getPaintingExhibitions(memberId, page);
        return ResponseEntity.ok(paintingExhibitions);
    }

    @Operation(summary = " artistExhibition 전시회 조회", description = "페이징 기능 포함")
    @GetMapping("/artistExhibition")
    public ResponseEntity<List<ExhibitionResponseDto.ExhibitionGeneralResponseDto>> getArtistExhibitionExhibitions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "1") int page) {
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> artistExhibitionExhibitions = exhibitionGenreService.getArtistExhibitionExhibitions(memberId, page);
        return ResponseEntity.ok(artistExhibitionExhibitions);
    }




}