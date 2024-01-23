package com.example.demo.domain.exhibition.service;





import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

import java.time.LocalDate;
import java.util.List;

public interface ExhibitionService {
    ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate,int page, ExhibitionRequestDto requestDto);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto, @MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate, int page);



    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, @MemberInfo MemberInfoDto memberInfoDto, int page);
    ExhibitionResponseDto.ExhibitionSpecificResponseDto getExhibitionById(Long id);
        List<Exhibition> getAllExhibitions();
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) ;
     List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);







}