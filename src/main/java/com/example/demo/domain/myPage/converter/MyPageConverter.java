package com.example.demo.domain.myPage.converter;


import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.ScrapMember;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.entity.StoryPicture;
import org.springframework.stereotype.Component;


@Component
public class MyPageConverter {

    public MyPageResponseDto.MemberGeneralResponseDto convertToGeneralDto(Member member){
        MyPageResponseDto.MemberGeneralResponseDto dto = MyPageResponseDto.MemberGeneralResponseDto.builder()
                .image(member.getImage())
                .nickname(member.getNickname())
                .introduction(member.getIntroduction())
                .myKeyword(member.getMyKeyword())
                .build();
        return dto;
    }

    public MyPageResponseDto.MyStoryResponseDto convertToStoryDto(Story story){
        MyPageResponseDto.MyStoryResponseDto dto = MyPageResponseDto.MyStoryResponseDto.builder()
                .storyId(story.getId())
                .storyTitle(story.getStoryTitle())
                .storyImage(story.getStoryThumbnailImage())
                .build();
        return dto;

    }
    public MyPageResponseDto.MyAlbumResponseDto convertToMyAlbumDto(StoryPicture storyPicture) {
        return MyPageResponseDto.MyAlbumResponseDto.builder()
                .storyPictureId(storyPicture.getId())
                .pictureUrl(storyPicture.getPictureUrl())
                .build();
    }
    public MyPageResponseDto.ScrappedStoryResponseDto convertToScrappedStory(ScrapStory scrapStory, Boolean isLiked) {
        return MyPageResponseDto.ScrappedStoryResponseDto.builder()
                .storyId(scrapStory.getStory().getId())
                .storyImage(scrapStory.getStory().getStoryThumbnailImage())
                .storyTitle(scrapStory.getStory().getStoryTitle())
                .isScrapped(scrapStory.getIsScrapped())
                .isLiked(isLiked)
                .build();
    }
    public MyPageResponseDto.ScrappedMemberResponseDto convertToScrappedMemberDto(ScrapMember scrapMember) {
        return MyPageResponseDto.ScrappedMemberResponseDto.builder()
                .memberId(scrapMember.getToMember().getMemberId())
                .isScrapped(scrapMember.getIsScrapped())
                .profile(scrapMember.getToMember().getImage())
                .memberNickname(scrapMember.getToMember().getNickname())
                .build();
    }

}
