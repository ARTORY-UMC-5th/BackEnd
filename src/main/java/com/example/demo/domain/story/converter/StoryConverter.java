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
    public Story convertToEntity(StoryRequestDto storyRequestDto, Member member, Exhibition exhibition) {
        return Story.builder()
                .storyTitle(storyRequestDto.getStoryTitle())
                .storySatisfactionLevel(storyRequestDto.getStorySatisfactionLevel())
                .storyWeather(storyRequestDto.getStoryWeather())
                .storyCompanion(storyRequestDto.getStoryCompanion())
                .storyKeyword(storyRequestDto.getStoryKeyword())
                .storyViewingTime(storyRequestDto.getStoryViewingTime())
                .storyContext(storyRequestDto.getStoryContext())
                .genre1(storyRequestDto.getGenre1())
                .genre2(storyRequestDto.getGenre2())
                .genre3(storyRequestDto.getGenre3())
                .isOpen(storyRequestDto.getIsOpen())
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