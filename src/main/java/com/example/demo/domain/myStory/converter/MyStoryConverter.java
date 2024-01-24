package com.example.demo.domain.myStory.converter;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MyStoryConverter {

    public MyStoryResponseDto convertToGeneralDto(Member member){
        MyStoryResponseDto dto = MyStoryResponseDto.builder()
                .image(member.getImage())
                .nickname(member.getNickname())
                .memo(member.getMemo())
                .build();
        return dto;
    }

    public MyStoryResponseDto.ExhibitionGeneralResponseDto convertToExhibitionDto(Exhibition exhibition, Boolean isLiked, Boolean isScrapped){
        MyStoryResponseDto.ExhibitionGeneralResponseDto dto = MyStoryResponseDto.ExhibitionGeneralResponseDto.builder()
                .ExhibitionId(exhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .isLiked(isLiked != null && isLiked)
                .isScrapped(isScrapped != null && isScrapped)
                .build();
        return dto;
    }


}
