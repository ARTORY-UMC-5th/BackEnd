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
/*
    public TestExhibitionResponseDto.ExhibitionGeneralResponseDto convertToGeneralDto(LikeExhibition likeExhibition) {
        return TestExhibitionResponseDto.ExhibitionGeneralResponseDto.builder()
                .id(likeExhibition.getId())
                .exhibitionTitle(likeExhibition.getExhibitionTitle())
                .exhibitionImage(likeExhibition.getExhibitionImage())
                .isLiked(likeExhibition.isLiked())
                .build();
    }
*/

    @Autowired
    private ExhibitionRepository exhibitionRepository;  // Exhibition 엔티티를 조회하기 위한 Repository 추가

    public TestExhibitionResponseDto.ExhibitionGeneralResponseDto convertToGeneralDto(LikeExhibition likeExhibition) {
        Exhibition exhibition = exhibitionRepository.findById(likeExhibition.getExhibition().getId())
                .orElseThrow(() -> new EntityNotFoundException("Exhibition not found with id: " + likeExhibition.getExhibition().getId()));

        return TestExhibitionResponseDto.ExhibitionGeneralResponseDto.builder()
                .id(likeExhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .isLiked(likeExhibition.isLiked())
                .build();
    }
}
