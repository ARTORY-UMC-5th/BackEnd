package com.example.demo.domain.exhibition.converter;



import com.example.demo.api.exhibition.dto.ExhibitionInfoResponseDto;
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

    public ExhibitionResponseDto.GenreCategoryResponseDto convertToGenreCategoryDto(Exhibition exhibition) {
        return ExhibitionResponseDto.GenreCategoryResponseDto.builder()
                .exhibitionId(exhibition.getId())
                .exhibitionImage(exhibition.getExhibitionImage())
                .genre(exhibition.getGenreCategory1())
                .build();
    }

    public ExhibitionResponseDto.ExhibitionGeneralOneResponseDto convertToOneDto(Exhibition exhibition) {
        return ExhibitionResponseDto.ExhibitionGeneralOneResponseDto.builder()
                .ExhibitionId(exhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .build();
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



    public Exhibition convertToEntity(ExhibitionInfoResponseDto.ExhibitionInfo exhibitionInfo, int like_count, Boolean isStarted, Boolean isEnded, String viewing_time) {

        Exhibition exhibition = Exhibition.builder()
                .exhibitionAddress(exhibitionInfo.getExhibition_address())
                .exhibitionDuration(exhibitionInfo.getExhibition_duration())
                .exhibitionImage(exhibitionInfo.getExhibition_image())
                .exhibitionInstitution(exhibitionInfo.getExhibition_institution())
                .exhibitionLatitude(exhibitionInfo.getExhibition_latitude())
                .exhibitionLongitude(exhibitionInfo.getExhibition_longitude())
                .exhibitionPlace(exhibitionInfo.getExhibition_place())
                .exhibitionTitle(exhibitionInfo.getExhibition_title())
                .exhibitionUrl(exhibitionInfo.getExhibition_url())
                .exhibitionViewingAge(exhibitionInfo.getExhibition_viewing_age())
                .exhibitionStartDate(exhibitionInfo.getStart_date())
                .exhibitionEndDate(exhibitionInfo.getEnd_date())
                .exhibitionLikeCount(like_count)
                .isEnded(isEnded)
                .isStarted(isStarted)
                .exhibitionViewingTime(viewing_time)
                .exhibitionPrice(exhibitionInfo.getExhibition_price())
                .build();

        return exhibition;
    }
}