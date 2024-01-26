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
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.myPage.converter.MyPageConverter;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;
    private final MyPageConverter myPageConverter;
    private final ExhibitionRepository exhibitionRepository;
    private final StoryRepository storyRepository;
    @Override
    public MyPageResponseDto.MemberGeneralResponseDto getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        return myPageConverter.convertToGeneralDto(member);
    }

    @Override
    public void updateMemberInfo(@MemberInfo MemberInfoDto memberInfoDto, String introduction, String myKeyword, String nickname, String image) {
        Long memberId = memberInfoDto.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        member.setIntroduction(introduction);
        member.setMyKeyword(myKeyword);
        member.setNickname(nickname);
        member.setImage(image);

        memberRepository.save(member);
    }

    @Override
    @Transactional
    public MyPageResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Story> storyPage = storyRepository.findAllByOrderByUpdateTimeExhibition(memberId, pageable);

        List<MyPageResponseDto.StoryGeneralResponseDto> stories = storyPage.getContent()
                .stream()
                .map(story -> myPageConverter.convertToStoryDto(story))
                .collect(Collectors.toList());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        MyPageResponseDto.MemberGeneralResponseDto myPageResponseDto = myPageConverter.convertToGeneralDto(member);
        myPageResponseDto.setStories(stories);

        return myPageResponseDto;
    }
}
