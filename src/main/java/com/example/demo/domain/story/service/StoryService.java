//package com.example.demo.domain.story.service;
//
//import com.example.demo.domain.exhibition.entity.Exhibition;
//import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
//import com.example.demo.domain.member.entity.Member;
//import com.example.demo.domain.member.repository.MemberRepository;
//import com.example.demo.domain.story.converter.StoryConverter;
//import com.example.demo.domain.story.dto.StoryRequestDto;
//import com.example.demo.domain.story.dto.StoryResponseDto;
//import com.example.demo.domain.story.entity.Story;
//import com.example.demo.domain.story.repository.StoryRepository;
//import com.example.demo.global.error.ErrorCode;
//import com.example.demo.global.error.exception.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class StoryService {
//
//    private final MemberRepository memberRepository;
//    private final ExhibitionRepository exhibitionRepository;
//    private final StoryRepository storyRepository;
//    private final StoryConverter storyConverter;
//
//
//
//    @Transactional
//    public void saveStory(StoryRequestDto storyRequestDto) {
//        Long memberId = storyRequestDto.getMemberId();
//        Long exhibitionId = storyRequestDto.getExhibitionId();
//
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
//
//        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
//                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EXHIBITION_NOT_EXISTS));
//
//        Story story = storyConverter.convertToEntity(storyRequestDto, member, exhibition);
//        storyRepository.save(story);
//    }
//
//
//}
