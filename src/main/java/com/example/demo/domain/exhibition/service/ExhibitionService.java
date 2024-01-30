package com.example.demo.domain.exhibition.service;





import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

import java.time.LocalDate;
import java.util.List;

public interface ExhibitionService {
    ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate,int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto, @MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate, int page);



    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, @MemberInfo MemberInfoDto memberInfoDto, int page);
    ExhibitionResponseDto.ExhibitionSpecificResponseDto getExhibitionById(Long id);
    List<Exhibition> getAllExhibitions();
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);
    ExhibitionResponseDto.ExhibitionGeneralOneResponseDto getRandomOneExhibition();
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) ;
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page);

//각 페이지 눌렀을 때
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions1(ExhibitionRequestDto requestDto, @MemberInfo MemberInfoDto memberInfoDto, int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions1(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate, int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions1(@MemberInfo MemberInfoDto memberInfoDto, int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions1(@MemberInfo MemberInfoDto memberInfoDto, int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions1(@MemberInfo MemberInfoDto memberInfoDto, int page) ;
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions1(@MemberInfo MemberInfoDto memberInfoDto, int page);






}