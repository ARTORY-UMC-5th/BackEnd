package com.example.demo.domain.exhibition.service;





import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;

import java.util.List;

public interface ExhibitionService {
    ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(int page, ExhibitionRequestDto requestDto);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto, int page);
//    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(int page);

    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, int page);
    ExhibitionResponseDto.ExhibitionSpecificResponseDto getExhibitionById(Long id);

    List<Exhibition> getAllExhibitions();

    //여기서부터는 형태만 만들어놓음, 다시 구현해야함
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(int page);
    List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(int page);






}