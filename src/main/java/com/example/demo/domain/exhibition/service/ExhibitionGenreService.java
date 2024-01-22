package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;

import java.util.List;

public interface ExhibitionGenreService {


    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getMediaExhibitions(Long memberId, int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getCraftExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDesignExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPictureExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSpecialExhibitionExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSculptureExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPlanExhibitionExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getInstallationArtExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPaintingExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getArtistExhibitionExhibitions(Long memberId, int page);



}