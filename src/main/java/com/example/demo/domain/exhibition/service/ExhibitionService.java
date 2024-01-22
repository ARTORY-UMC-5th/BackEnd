package com.example.demo.domain.exhibition.service;





import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;

import java.util.List;

public interface ExhibitionService {
    ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(Long memberId, int page, ExhibitionRequestDto requestDto);
   List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto, Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(Long memberId, int page);



    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(Long memberId, int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, Long memberId, int page);
    ExhibitionResponseDto.ExhibitionSpecificResponseDto getExhibitionById(Long id);
    List<Exhibition> getAllExhibitions();
 List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(Long memberId, int page);

    //    //여기서부터는 형태만 만들어놓음, 다시 구현해야함

//    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(int page);
//    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(int page);
//
//




}