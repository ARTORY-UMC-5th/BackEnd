package com.example.demo.domain.exhibition.converter;



import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionConverter {


    public ExhibitionResponseDto.ExhibitionGeneralResponseDto convertToGeneralDto(Exhibition exhibition, Boolean isLiked, Boolean isScrapped) {
        ExhibitionResponseDto.ExhibitionGeneralResponseDto dto = ExhibitionResponseDto.ExhibitionGeneralResponseDto.builder()
                .ExhibitionId(exhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .isLiked(isLiked != null && isLiked)
                .isScrapped(isScrapped != null && isScrapped)
                .build();
        return dto;
    }


    public ExhibitionResponseDto.ExhibitionSpecificResponseDto convertToSpecificDto(Exhibition exhibition) {
        return ExhibitionResponseDto.ExhibitionSpecificResponseDto.builder()
                .ExhibitionId(exhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .exhibitionAddress(exhibition.getExhibitionAddress())
                .exhibitionPlace(exhibition.getExhibitionPlace())
                .exhibitionDuration(exhibition.getExhibitionDuration())
                .exhibitionInstitution(exhibition.getExhibitionInstitution())
                .exhibitionViewingTime(exhibition.getExhibitionViewingTime())
                .exhibitionViewingAge(exhibition.getExhibitionViewingAge())
                .exhibitionPrice(exhibition.getExhibitionPrice())
                .exhibitionUrl(exhibition.getExhibitionUrl())
                .exhibitionLongitude(exhibition.getExhibitionLongitude())
                .exhibitionLatitude(exhibition.getExhibitionLatitude())
                .exhibitionStartDate(exhibition.getExhibitionStartDate())
                .exhibitionEndDate(exhibition.getExhibitionEndDate())
                .isEnded(exhibition.isEnded())
                .genreCategory1(exhibition.getGenreCategory1())
                .genreCategory2(exhibition.getGenreCategory2())
                .genreCategory3(exhibition.getGenreCategory3())
                .build();
    }


}