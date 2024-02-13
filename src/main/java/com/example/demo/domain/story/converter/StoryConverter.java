package com.example.demo.domain.story.converter;


import com.example.demo.domain.comment.dto.CommentResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.story.constant.State;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoryConverter {
    public Story convertToEntity(StoryRequestDto.StoryRequestGeneralDto storyRequestDto, Member member, Exhibition exhibition) {

        Genre genre1 = Genre.fromString(storyRequestDto.getGenre1());
        Genre genre2 = Genre.fromString(storyRequestDto.getGenre2());
        Genre genre3 = Genre.fromString(storyRequestDto.getGenre3());

        return Story.builder()
                .storyTitle(storyRequestDto.getStoryTitle())
                .storySatisfactionLevel(storyRequestDto.getStorySatisfactionLevel())
                .storyWeather(storyRequestDto.getStoryWeather())
                .storyCompanion(storyRequestDto.getStoryCompanion())
                .storyKeyword(storyRequestDto.getStoryKeyword())
                .storyViewingTime(storyRequestDto.getStoryViewingTime())
                .storyContext(storyRequestDto.getStoryContext())
                .year(storyRequestDto.getYear())
                .month(storyRequestDto.getMonth())
                .day(storyRequestDto.getDay())
                .genre1(genre1)
                .genre2(genre2)
                .genre3(genre3)
                .isOpen(storyRequestDto.getIsOpen())
                .storyState(State.DONE)
                .member(member)
                .exhibition(exhibition)
                .build();
    }

    public Story convertToDateEntity(StoryRequestDto.StoryRequestDateDto storyRequestDto, Member member, Exhibition exhibition) {
        return Story.builder()
                .exhibition(exhibition)
                .member(member)
                .year(storyRequestDto.getYear())
                .month(storyRequestDto.getMonth())
                .day(storyRequestDto.getDay())
                .storyState(State.NOT_STARTED)
                .build();
    }

    // story 엔티티를 dto로 변환
    public StoryResponseDto.StorySpecificResponseDto convertToSpecificResponseDto(Story story, Boolean isMemberScrapped, List<CommentResponseDto> commentResponseDtoList){

        // 스토리생성한 멤버
        Member member = story.getMember();

        // 해당 전시회
        Exhibition exhibition = story.getExhibition();


        StoryResponseDto.StorySpecificResponseDto storySpecificResponseDto = StoryResponseDto.StorySpecificResponseDto.builder()
                .memberId(member.getMemberId())
                .memberNickname(member.getNickname())
                .memberProfile(member.getImage())
                // 조회하는 멤버가 스토리를 생성한 멤버를 스크랩했는지
                .isScrapped(isMemberScrapped)

                .exhibitionId(exhibition.getId())
                .exhibitionImage(exhibition.getExhibitionImage())
                .exhibitionTitle(exhibition.getExhibitionTitle())

                .storyId(story.getId())
                .storyTitle(story.getStoryTitle())
                .storySatisfactionLevel(story.getStorySatisfactionLevel())
                .storyWeather(story.getStoryWeather())
                .storyCompanion(story.getStoryCompanion())
                .storyViewingTime(story.getStoryViewingTime())
                .year(story.getYear())
                .month(story.getMonth())
                .day(story.getDay())
                .storyGenre1(story.getGenre1())
                .storyGenre2(story.getGenre2())
                .storyGenre3(story.getGenre3())
                .storyKeyword(story.getStoryKeyword())
                .storyContext(story.getStoryContext())
                .commentResponseDtoList(commentResponseDtoList)
                .build();


        return  storySpecificResponseDto;
    }


    public static StoryResponseDto.StoryThumbnailResponseDto convertToStoryThumbnailResponseDto(Story story, Boolean isLiked, Boolean isScrapped){

        StoryResponseDto.StoryThumbnailResponseDto storyThumbnailResponseDto = StoryResponseDto.StoryThumbnailResponseDto.builder()
                .storyId(story.getId())
                .storyTitle(story.getStoryTitle())
                .storyImage(story.getStoryThumbnailImage())
                .isLiked(isLiked)
                .isScrapped(isScrapped)
                .build();

        return storyThumbnailResponseDto;
    }

    public static StoryResponseDto.MemberThumbnailResponseDto convertToMemberThumbnailResponseDto(Member member, Boolean isScrapped) {

        StoryResponseDto.MemberThumbnailResponseDto memberThumbnailResponseDto = StoryResponseDto.MemberThumbnailResponseDto.builder()
                .memberId(member.getMemberId())
                .memberNickname(member.getNickname())
                .memberProfile(member.getImage())
                .isScrapped(isScrapped)
                .build();

        return memberThumbnailResponseDto;
    }

    public static StoryResponseDto.StoryRepositoryResponseDto convertToStoryRepositoryResponseDto(Story story, Boolean isScrapped) {

        StoryResponseDto.StoryRepositoryResponseDto storyRepositoryResponseDto = StoryResponseDto.StoryRepositoryResponseDto.builder()
                .story(story)
                .isMemberScrapped(isScrapped)
                .build();

        return storyRepositoryResponseDto;
    }

    // 임시 저장 데이터를 스토리로 변경 (storyId가 없을 때)
    public Story convertFromDraftToEntity(StoryRequestDto.StoryRequestGeneralDto draftStoryRequestDto, Member member, Exhibition exhibition) {

        Genre genre1 = Genre.fromString(draftStoryRequestDto.getGenre1());
        Genre genre2 = Genre.fromString(draftStoryRequestDto.getGenre2());
        Genre genre3 = Genre.fromString(draftStoryRequestDto.getGenre3());

        Story story = Story.builder()
                .exhibition(exhibition)
                .member(member)
                .storyTitle(draftStoryRequestDto.getStoryTitle())
                .storySatisfactionLevel(draftStoryRequestDto.getStorySatisfactionLevel())
                .storyWeather(draftStoryRequestDto.getStoryWeather())
                .storyCompanion(draftStoryRequestDto.getStoryCompanion())
                .storyKeyword(draftStoryRequestDto.getStoryKeyword())
                .storyViewingTime(draftStoryRequestDto.getStoryViewingTime())
                .year(draftStoryRequestDto.getYear())
                .month(draftStoryRequestDto.getMonth())
                .day(draftStoryRequestDto.getDay())
                .storyContext(draftStoryRequestDto.getStoryContext())
                .genre1(genre1)
                .genre2(genre2)
                .genre3(genre3)
                .isOpen(draftStoryRequestDto.getIsOpen())
                .storyState(State.IN_PROGRESS)
                .build();

        return story;
    }

    // 임시 저장 데이터를 스토리로 변경 (storyId가 있을 때)
    public Story convertFromDraftToEntityWithStoryId(StoryRequestDto.StoryRequestGeneralDto draftStoryRequestDto, Member member, Exhibition exhibition, Story existingStory, Long storyId) {

        Genre genre1 = Genre.fromString(draftStoryRequestDto.getGenre1());
        Genre genre2 = Genre.fromString(draftStoryRequestDto.getGenre2());
        Genre genre3 = Genre.fromString(draftStoryRequestDto.getGenre3());

        Story story = existingStory.builder()
                .exhibition(exhibition)
                .member(member)
                .id(storyId)
                .storyTitle(draftStoryRequestDto.getStoryTitle())
                .storySatisfactionLevel(draftStoryRequestDto.getStorySatisfactionLevel())
                .storyWeather(draftStoryRequestDto.getStoryWeather())
                .storyCompanion(draftStoryRequestDto.getStoryCompanion())
                .storyKeyword(draftStoryRequestDto.getStoryKeyword())
                .storyViewingTime(draftStoryRequestDto.getStoryViewingTime())
                .year(draftStoryRequestDto.getYear())
                .month(draftStoryRequestDto.getMonth())
                .day(draftStoryRequestDto.getDay())
                .storyContext(draftStoryRequestDto.getStoryContext())
                .genre1(genre1)
                .genre2(genre2)
                .genre3(genre3)
                .isOpen(draftStoryRequestDto.getIsOpen())
                .storyState(State.IN_PROGRESS)
                .build();

        return story;
    }

    public Story convertToEntityWithStoryId(StoryRequestDto.StoryRequestGeneralDto storyRequestDto, Member member, Exhibition exhibition, Story existingStory, Long storyId) {

        Genre genre1 = Genre.fromString(storyRequestDto.getGenre1());
        Genre genre2 = Genre.fromString(storyRequestDto.getGenre2());
        Genre genre3 = Genre.fromString(storyRequestDto.getGenre3());

        Story story = existingStory.builder()
                .exhibition(exhibition)
                .member(member)
                .id(storyId)
                .storyTitle(storyRequestDto.getStoryTitle())
                .storySatisfactionLevel(storyRequestDto.getStorySatisfactionLevel())
                .storyWeather(storyRequestDto.getStoryWeather())
                .storyCompanion(storyRequestDto.getStoryCompanion())
                .storyKeyword(storyRequestDto.getStoryKeyword())
                .storyViewingTime(storyRequestDto.getStoryViewingTime())
                .year(storyRequestDto.getYear())
                .month(storyRequestDto.getMonth())
                .day(storyRequestDto.getDay())
                .storyContext(storyRequestDto.getStoryContext())
                .genre1(genre1)
                .genre2(genre2)
                .genre3(genre3)
                .isOpen(storyRequestDto.getIsOpen())
                .storyState(State.DONE)
                .build();

        return story;


    }
}