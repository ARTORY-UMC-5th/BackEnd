package com.example.demo.domain.exhibition.converter;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionConverter {

    public ExhibitionResponseDto.ExhibitionRecentResponseDto convertToDto(Exhibition exhibition) {
        return ExhibitionResponseDto.ExhibitionRecentResponseDto.builder()
                .id(exhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .build();
    }
}