package com.example.demo.domain.myStory.converter;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyStoryConverter {





    private boolean isDateWithinExhibition(Exhibition exhibition, int year, int month, int day) {
        LocalDate requestedDate = LocalDate.of(year, month, day);

        LocalDate startDate = LocalDate.parse(exhibition.getExhibitionStartDate());
        LocalDate endDate = LocalDate.parse(exhibition.getExhibitionEndDate());

        return !requestedDate.isBefore(startDate) && !requestedDate.isAfter(endDate);
    }


    public MyStoryResponseDto.MemberGeneralResponseDto convertToGeneralDto(Member member){
        MyStoryResponseDto.MemberGeneralResponseDto dto = MyStoryResponseDto.MemberGeneralResponseDto.builder()
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

    public MyStoryResponseDto.StoryGeneralResponseDto convertToStoryDto(Story story){
        MyStoryResponseDto.StoryGeneralResponseDto dto = MyStoryResponseDto.StoryGeneralResponseDto.builder()
                .StoryId(story.getId())
                .exhibitionTitle(story.getExhibition().getExhibitionTitle())
                .year(story.getYear())
                .month(story.getMonth())
                .day(story.getDay())
                .storyState(story.getStoryState())
                .build();
        return dto;
    }

    public List<MyStoryResponseDto.DateInfoExhibitionResponseDto> convertToDateInfoExhibitionResponseDto(
            List<Exhibition> exhibitions, int year, int month, int day) {

        List<MyStoryResponseDto.DateInfoExhibitionResponseDto> responseDtos = new ArrayList<>();

        for (Exhibition exhibition : exhibitions) {
            if (isDateWithinExhibition(exhibition, year, month, day)) {
                MyStoryResponseDto.DateInfoExhibitionResponseDto responseDto = MyStoryResponseDto.DateInfoExhibitionResponseDto.builder()
                        .ExhibitionId(exhibition.getId())
                        .exhibitionTitle(exhibition.getExhibitionTitle())
                        .exhibitionImage(exhibition.getExhibitionImage())
                        .build();
                responseDtos.add(responseDto);
            }
        }

        return responseDtos;
    }
    public List<MyStoryResponseDto.DateInfoExhibitionResponseDto> convertToDateInfoExhibitionResponseDto(
            List<Exhibition> exhibitions, int year, int month, int day, String title) {

        List<MyStoryResponseDto.DateInfoExhibitionResponseDto> responseDtos = new ArrayList<>();

        for (Exhibition exhibition : exhibitions) {
            if (isDateWithinExhibition(exhibition, year, month, day) && exhibition.getExhibitionTitle().contains(title)) {
                MyStoryResponseDto.DateInfoExhibitionResponseDto responseDto = MyStoryResponseDto.DateInfoExhibitionResponseDto.builder()
                        .ExhibitionId(exhibition.getId())
                        .exhibitionTitle(exhibition.getExhibitionTitle())
                        .exhibitionImage(exhibition.getExhibitionImage())
                        .build();
                responseDtos.add(responseDto);
            }
        }

        return responseDtos;
    }
    public MyStoryResponseDto.StorySpecificResponseDto convertToSpecificStoryDto(Story story, Exhibition exhibition){
        MyStoryResponseDto.StorySpecificResponseDto dto = MyStoryResponseDto.StorySpecificResponseDto.builder()
                .StoryId(story.getId())
                .storyState(story.getStoryState())
                .exhibitionId(exhibition.getId())
                .exhibitionImage(exhibition.getExhibitionImage())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .build();
        return dto;
    }

}
