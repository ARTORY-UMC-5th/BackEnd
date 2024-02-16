package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

import java.util.List;

public interface ExhibitionGenreService {

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getMediaExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getCraftExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDesignExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPictureExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSpecialExhibitionExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSculptureExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPlanExhibitionExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getInstallationArtExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPaintingExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getArtistExhibitionExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);



}