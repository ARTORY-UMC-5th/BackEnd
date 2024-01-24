package com.example.demo.domain.myStory.service;

import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.myStory.converter.MyStoryConverter;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
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
public class MyStoryServiceImpl implements MyStoryService{

    private final MemberRepository memberRepository;
    private final MyStoryConverter myStoryConverter;
    private final ExhibitionRepository exhibitionRepository;


//    public MyStoryResponseDto.MemberGeneralResponseDto getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {
//        Long memberId = memberInfoDto.getMemberId();
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));
//
//        return myStoryConverter.convertToGeneralDto(member);
//    }
//
//    @Transactional
//    public List<MyStoryResponseDto.ExhibitionGeneralResponseDto> getScrappedExhibitionInfo(@MemberInfo MemberInfoDto memberInfoDto, int page) {
//        Long memberId = memberInfoDto.getMemberId();
//
//        int pageSize = 10;
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        Page<Object[]> scrapExhibitionsPage = exhibitionRepository.findAllByOrderByUpdateTimeExhibition(memberId, pageable);
//        System.out.println("scrapExhibitionsPage = " + scrapExhibitionsPage);
//        List<MyStoryResponseDto.ExhibitionGeneralResponseDto> exhibitions = scrapExhibitionsPage.getContent()
//                .stream()
//                .map(array -> {
//                    ScrapExhibition scrapExhibition = (ScrapExhibition) array[0];
//                    Boolean isLiked = (Boolean) array[1];
//                    Boolean isScrapped =  (Boolean) array[2];
//                    System.out.println("isScrapped = " + isScrapped);
//                    System.out.println("isLiked = " + isLiked);
//                    System.out.println("scrapExhibition = " + scrapExhibition);
//                    return myStoryConverter.convertToExhibitionDto(scrapExhibition.getExhibition(), isLiked, isScrapped);
//                })
//                .collect(Collectors.toList());
//
//        return exhibitions;
//    }

    @Override
    @Transactional

    public MyStoryResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> scrapExhibitionsPage = exhibitionRepository.findAllByOrderByUpdateTimeExhibition(memberId, pageable);

        List<MyStoryResponseDto.ExhibitionGeneralResponseDto> exhibitions = scrapExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    ScrapExhibition scrapExhibition = (ScrapExhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];

                    return myStoryConverter.convertToExhibitionDto(scrapExhibition.getExhibition(), isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        MyStoryResponseDto.MemberGeneralResponseDto myStoryResponseDto = myStoryConverter.convertToGeneralDto(member);
        myStoryResponseDto.setExhibitions(exhibitions);

        return myStoryResponseDto;
    }

}
