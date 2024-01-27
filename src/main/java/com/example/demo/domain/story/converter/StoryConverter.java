package com.example.demo.domain.story.converter;


import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.Story;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class StoryConverter {
    public Story convertSaveToEntity(StoryRequestDto.StorySaveRequestDto storySaveRequestDto, Member member, Exhibition exhibition) {
        return Story.builder()
                .storyTitle(storySaveRequestDto.getStoryTitle())
                .storySatisfactionLevel(storySaveRequestDto.getStorySatisfactionLevel())
                .storyWeather(storySaveRequestDto.getStoryWeather())
                .storyCompanion(storySaveRequestDto.getStoryCompanion())
                .storyKeyword(storySaveRequestDto.getStoryKeyword())
                .storyViewingTime(storySaveRequestDto.getStoryViewingTime())
                .storyContext(storySaveRequestDto.getStoryContext())
                .genre1(storySaveRequestDto.getGenre1())
                .genre2(storySaveRequestDto.getGenre2())
                .genre3(storySaveRequestDto.getGenre3())
                .isOpen(storySaveRequestDto.getIsOpen())
                .member(member)
                .exhibition(exhibition)
                .build();
    }

    public Story convertUpdateToEntity(StoryRequestDto.StoryUpdateRequestDto storyUpdateRequestDto, Member member, Exhibition exhibition) {
        return Story.builder()
                .storyTitle(storyUpdateRequestDto.getStoryTitle())
                .storySatisfactionLevel(storyUpdateRequestDto.getStorySatisfactionLevel())
                .storyWeather(storyUpdateRequestDto.getStoryWeather())
                .storyCompanion(storyUpdateRequestDto.getStoryCompanion())
                .storyKeyword(storyUpdateRequestDto.getStoryKeyword())
                .storyViewingTime(storyUpdateRequestDto.getStoryViewingTime())
                .storyContext(storyUpdateRequestDto.getStoryContext())
                .genre1(storyUpdateRequestDto.getGenre1())
                .genre2(storyUpdateRequestDto.getGenre2())
                .genre3(storyUpdateRequestDto.getGenre3())
                .isOpen(storyUpdateRequestDto.getIsOpen())
                .member(member)
                .exhibition(exhibition)
                .build();
    }


    // story 엔티티를 dto로 변환
    public StoryResponseDto.StorySpecificResponseDto convertToSpecificResponseDto(Story story, Boolean isMemberScrapped){

        // 스토리생성한 멤버
        Member member = story.getMember();

        // 해당 전시회
        Exhibition exhibition = story.getExhibition();


        StoryResponseDto.StorySpecificResponseDto storySpecificResponseDto = StoryResponseDto.StorySpecificResponseDto.builder()
                .memberId(member.getMemberId())
                .memberNickname(member.getNickname())
                .memberProfile(member.getProfile())
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
                .storyGenre1(story.getGenre1())
                .storyGenre2(story.getGenre2())
                .storyGenre3(story.getGenre3())
                .storyKeyword(story.getStoryKeyword())
                .storyContext(story.getStoryContext())
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
                .memberProfile(member.getProfile())
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
}