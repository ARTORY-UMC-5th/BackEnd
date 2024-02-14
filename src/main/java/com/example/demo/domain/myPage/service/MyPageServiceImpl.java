//package com.example.demo.domain.myPage.service;
//
//
//import com.example.demo.domain.member.entity.Member;
//import com.example.demo.domain.member.repository.MemberRepository;
//import com.example.demo.domain.myPage.converter.MyPageConverter;
//import com.example.demo.domain.myPage.dto.MyPageResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//@Service
//@RequiredArgsConstructor
//public class MyPageService {
//
//    private final MemberRepository memberRepository;
//    private final MyPageConverter myPageConverter;
//
//
//
//    public MyPageResponseDto getMemberInfo(Long memberId) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));
//
//        return myPageConverter.convertToGeneralDto(member);
//    }
//
//    public void updateMemberInfo(Long memberId, String introduction, String myKeyword, String nickname, String image) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));
//
//        member.setIntroduction(introduction);
//        member.setMyKeyword(myKeyword);
//        member.setNickname(nickname);
//        member.setImage(image);
//
//        memberRepository.save(member);
//    }
//}

package com.example.demo.domain.myPage.service;

import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.ScrapMember;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.repository.ScrapMemberRepository;
import com.example.demo.domain.myPage.converter.MyPageConverter;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.story.entity.LikeStory;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.entity.StoryPicture;
import com.example.demo.domain.story.repository.LikeStoryRepository;
import com.example.demo.domain.story.repository.ScrapStoryRepository;
import com.example.demo.domain.story.repository.StoryPictureRepository;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;
    private final MyPageConverter myPageConverter;
    private final ExhibitionRepository exhibitionRepository;
    private final StoryRepository storyRepository;
    private final StoryPictureRepository storyPictureRepository;
    private final LikeStoryRepository likeStoryRepository;
    private final ScrapMemberRepository scrapMemberRepository;
    private final ScrapStoryRepository scrapStoryRepository;
    @Override
    public MyPageResponseDto.MemberGeneralResponseDto getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        return myPageConverter.convertToGeneralDto(member);
    }

    @Override
    public void updateMemberInfo(@MemberInfo MemberInfoDto memberInfoDto, String userName, String introduction, String myKeyword, String nickname, String image) {
        Long memberId = memberInfoDto.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        member.setMemberName(userName);
        member.setIntroduction(introduction);
        member.setMyKeyword(myKeyword);
        member.setNickname(nickname);
        member.setImage(image);

        memberRepository.save(member);
    }

    @Override
    @Transactional
    public MyPageResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        List<Story> allStories = storyRepository.findAllByOrderByUpdateTimeExhibition(memberId);

        List<MyPageResponseDto.MyStoryResponseDto> stories = getAllStory(memberId);
        List<MyPageResponseDto.MyAlbumResponseDto> allStoryPictures = getAllStoryPictures(memberId);
        List<MyPageResponseDto.ScrappedStoryResponseDto> scrappedStories = getScrappedStories(memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));
        List<MyPageResponseDto.ScrappedMemberResponseDto> scrappedMembers = getScrappedMembers(memberId);

        MyPageResponseDto.MemberGeneralResponseDto myPageResponseDto = myPageConverter.convertToGeneralDto(member);
        myPageResponseDto.setStories(stories);
        myPageResponseDto.setStoryPictures(allStoryPictures);
        myPageResponseDto.setScrappedStories(scrappedStories);
        myPageResponseDto.setScrappedMembers(scrappedMembers);

        return myPageResponseDto;
    }

    //내 스토리 위한 메서드

    private List<MyPageResponseDto.MyStoryResponseDto> getAllStory(Long memberId) {
        List<Story> memberStories = storyRepository.findByMemberId(memberId);

        return memberStories.stream()
                .map(story -> myPageConverter.convertToStoryDto(story))
                .collect(Collectors.toList());
    }


    //사진 위한 메서드


    private List<MyPageResponseDto.MyAlbumResponseDto> getAllStoryPictures(Long memberId) {

        List<StoryPicture> memberStoryPictures = storyPictureRepository.findAllByMemberId(memberId);

        return memberStoryPictures.stream()
                .map(myPageConverter::convertToMyAlbumDto)
                .collect(Collectors.toList());
    }


    //저장 스토리
    private List<MyPageResponseDto.ScrappedStoryResponseDto> getScrappedStories(Long memberId) {
        List<ScrapStory> scrapStoryList = scrapStoryRepository.findAllByScrapMemberId(memberId);

        List<MyPageResponseDto.ScrappedStoryResponseDto> scrappedStoryResponseDtos = scrapStoryList.stream()
            //story를 ScrappedStoryResponseDto로 변경
            .map((ScrapStory scrapStory) -> {
                LikeStory likeStory = likeStoryRepository.findByMemberIdAndStoryId(memberId, scrapStory.getStory().getId());
                if (likeStory == null) {
                    Boolean isLiked = false;
                    return myPageConverter.convertToScrappedStory(scrapStory, isLiked);
                } else {
                    return myPageConverter.convertToScrappedStory(scrapStory, likeStory.getIsLiked());
                }
            })
            .collect(Collectors.toList()); // collect 추가

//        for (Story story : stories) {
//            for (ScrapStory scrapStory : story.getScrapStoryList()) {
//                if (scrapStory.getMember().getMemberId().equals(memberId)) {
//                    LikeStory likeStory = likeStoryRepository.findByMemberAndStory(scrapStory.getMember(), scrapStory.getStory());
//                    boolean isLiked = likeStory != null && likeStory.getIsLiked();
//
//                    MyPageResponseDto.ScrappedStoryResponseDto scrappedStoryDto = myPageConverter.convertToScrappedStory(scrapStory);
//                    scrappedStoryDto.setIsLiked(isLiked);
//                    scrappedStories.add(scrappedStoryDto);
//                }
//            }
//        }

        return scrappedStoryResponseDtos;
    }

    //저장 유저
    private List<MyPageResponseDto.ScrappedMemberResponseDto> getScrappedMembers(Long memberId)  {
        List<ScrapMember> scrapMembers = scrapMemberRepository.findByFromMemberId(memberId);

        return scrapMembers.stream()
                .map(myPageConverter::convertToScrappedMemberDto)
                .collect(Collectors.toList());
    }
}
