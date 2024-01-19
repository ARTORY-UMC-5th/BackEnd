package com.example.demo.domain.exhibition.converter;

import com.example.demo.domain.exhibition.dto.TestExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.LikeExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestExhibitionConverter {
    public TestExhibitionResponseDto.ExhibitionGeneralResponseDto convertToGeneralDto(LikeExhibition likeExhibition) {
        return TestExhibitionResponseDto.ExhibitionGeneralResponseDto.builder()
                .id(likeExhibition.getExhibition().getId())
                .exhibitionTitle(likeExhibition.getExhibition().getExhibitionTitle())
                .exhibitionImage(likeExhibition.getExhibition().getExhibitionImage())
                .isLiked(likeExhibition.isLiked())
                .build();
    }

}