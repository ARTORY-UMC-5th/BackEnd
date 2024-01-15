package com.example.demo.domain.exhibition.service;


import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;

import java.util.List;

public interface ExhibitionService {
    List<ExhibitionResponseDto.ExhibitionRecentResponseDto> getRecentExhibitions(int page);
    ExhibitionResponseDto.ExhibitionRecentResponseDto convertToDto(Exhibition exhibition);
}