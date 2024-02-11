




package com.example.demo.domain.myStory.service;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.myStory.converter.MyStoryConverter;
import com.example.demo.domain.myStory.dto.MyStoryRequestDto;
import com.example.demo.domain.myStory.dto.MyStoryResponseDto;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyStoryServiceImpl implements MyStoryService{

    private final MemberRepository memberRepository;
    private final MyStoryConverter myStoryConverter;
    private final ExhibitionRepository exhibitionRepository;
    private final StoryRepository storyRepository;


    @Override
    @Transactional
    public List<MyStoryResponseDto.StorySpecificResponseDto> getSavedStories(@MemberInfo MemberInfoDto memberInfoDto, int year, int month, int day) {
        Long memberId = memberInfoDto.getMemberId();

        List<Story> memberStories = storyRepository.findByMemberId(memberId);

        List<MyStoryResponseDto.StorySpecificResponseDto> storyDtos = memberStories.stream()
                .filter(story -> story.getYear() == year && story.getMonth() == month && story.getDay() == day)
                .map((Story story1) -> {
                    Exhibition exhibition = story1.getExhibition();
                    return myStoryConverter.convertToSpecificStoryDto(story1, exhibition);
                })
                .collect(Collectors.toList());

        return storyDtos;
    }

    @Override
    @Transactional

    public MyStoryResponseDto.MemberGeneralResponseDto getAllMyStoryInfo(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 12;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> scrapExhibitionsPage = exhibitionRepository.findAllByOrderByUpdateTimeExhibition(memberId, pageable);

        List<MyStoryResponseDto.ExhibitionGeneralResponseDto> exhibitions = scrapExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];

                    return myStoryConverter.convertToExhibitionDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        MyStoryResponseDto.MemberGeneralResponseDto myStoryResponseDto = myStoryConverter.convertToGeneralDto(member);

        // 첫 번째 메서드의 스토리 필터링 로직을 통합
        List<Story> memberStories = storyRepository.findByMemberId(memberId);
        List<MyStoryResponseDto.StoryGeneralResponseDto> storyDtos = memberStories.stream()
                .filter(story -> story.getYear() != 0 && story.getMonth() != 0 && story.getDay() != 0)
                .map(myStoryConverter::convertToStoryDto)
                .collect(Collectors.toList());

        myStoryResponseDto.setStories(storyDtos);
        myStoryResponseDto.setExhibitions(exhibitions);

        return myStoryResponseDto;
    }


    @Override
    public List<MyStoryResponseDto.DateInfoExhibitionResponseDto> getExhibitionsByDate(MyStoryRequestDto.DateInfoRequestDto dateInfoRequestDto) {
        int year = dateInfoRequestDto.getYear();
        int month = dateInfoRequestDto.getMonth();
        int day = dateInfoRequestDto.getDay();

        LocalDate requestedDate = LocalDate.of(year, month, day);

        List<Exhibition> exhibitions = exhibitionRepository.findAll();
        List<MyStoryResponseDto.DateInfoExhibitionResponseDto> result = myStoryConverter.convertToDateInfoExhibitionResponseDto(exhibitions, year, month, day);

        return result;
    }
    @Transactional
    @Override
    public void saveMemberMemo(@MemberInfo MemberInfoDto memberInfoDto, String memo) {
        Long memberId = memberInfoDto.getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. memberId: " + memberId));

        member.setMemo(memo);
        memberRepository.save(member);
    }


    public List<MyStoryResponseDto.DateInfoExhibitionResponseDto> getDateAndTitleRequestDto(
            @MemberInfo MemberInfoDto memberInfoDto, MyStoryRequestDto.DateAndTitleRequestDto dateInfoRequestDto, String title) {
        int year = dateInfoRequestDto.getYear();
        int month = dateInfoRequestDto.getMonth();
        int day = dateInfoRequestDto.getDay();

        List<Exhibition> exhibitions = exhibitionRepository.findAll();
        List<MyStoryResponseDto.DateInfoExhibitionResponseDto> result = myStoryConverter.convertToDateInfoExhibitionResponseDto(exhibitions, year, month, day, title);

        return result;
    }



}


