package com.example.demo.domain.story.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ExhibitionGenre;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.converter.StoryConverter;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.repository.ScrapStoryRepository;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.EntityNotFoundException;

import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.thymeleaf.util.StringUtils.capitalize;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{

    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final StoryRepository storyRepository;
    private final ScrapStoryRepository scrapStoryRepository;
    private final StoryConverter storyConverter;

    // 해당 스토리 id, 열람하는 memberId
    @Transactional
    public StoryResponseDto.StorySpecificResponseDto getStoryById(Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
        Optional<Story> optionalStory = storyRepository.findById(storyId);
        Long memberId = memberInfoDto.getMemberId();

        if (optionalStory.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.STORY_NOT_EXISTS);
        }

        Story story = optionalStory.get();

        ScrapStory scrapStory = scrapStoryRepository.findByStoryIdAndMemberId(storyId, memberId);
        Boolean isMemberScrapped = (scrapStory != null && scrapStory.getIsScrapped() != null) ? scrapStory.getIsScrapped() : false;


        return storyConverter.convertToSpecificResponseDto(story, isMemberScrapped);
    }


    @Override
    public StoryResponseDto.StoryListResponseDto getAllStoryList(int page,  @MemberInfo MemberInfoDto memberInfoDto) {

        StoryResponseDto.StoryListResponseDto allStoryDto = StoryResponseDto.StoryListResponseDto.builder()
                .poluarStoryDtoList(getPopularStories(page, memberInfoDto))
                .recommendStoryDtoList(getRecommendStories(page, memberInfoDto))
                .recentStoryDtoList(getRecentStories(page, memberInfoDto))
                .recommendMemberDtoList(getRecommendMembers(page, memberInfoDto))
                .build();

        return allStoryDto;
    }


    @Override
    public List<StoryResponseDto.StoryThumbnailResponseDto> getPopularStories(int page,  @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> popularStoryPage = storyRepository.findAllByOrderByStoryLikeCountDesc(pageable, memberId);

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = popularStoryPage.getContent()
                .stream()
                .map(array -> {
                    Story story = (Story) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return storyConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return popularStories;
    }


    @Override
    public List<StoryResponseDto.StoryThumbnailResponseDto> getRecommendStories(int page,  @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        // 추천 알고리즘은 추후 구현
        Page<Object[]> popularStoryPage = storyRepository.findAllByRecommend(pageable, memberId);

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = popularStoryPage.getContent()
                .stream()
                .map(array -> {
                    Story story = (Story) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return storyConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return popularStories;
    }


    @Override
    public List<StoryResponseDto.StoryThumbnailResponseDto> getRecentStories(int page,  @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> popularStoryPage = storyRepository.findAllByOrderByCreateTimeDesc(pageable, memberId);

        List<StoryResponseDto.StoryThumbnailResponseDto> popularStories = popularStoryPage.getContent()
                .stream()
                .map(array -> {
                    Story story = (Story) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return storyConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return popularStories;
    }


    @Transactional
    public List<StoryResponseDto.StoryThumbnailResponseDto> getSearchStoriesByTitle(int page, String title,  @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> searchStoryPage = storyRepository.findByStoryTitleContaining(title, pageable, memberId);

        List<StoryResponseDto.StoryThumbnailResponseDto> searchStories = searchStoryPage.getContent()
                .stream()
                .map(array -> {
                    Story story = (Story) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return storyConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
                })
                .toList();

        return searchStories;
    }


    @Override
    public List<StoryResponseDto.MemberThumbnailResponseDto> getRecommendMembers(int page,  @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        // 추후 구현
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> recommendMemberPage = memberRepository.recommendMemberWithScrapped(pageable, memberId);

        List<StoryResponseDto.MemberThumbnailResponseDto> recommendMembers = recommendMemberPage.getContent()
                .stream()
                .map(array -> {
                    Member member = (Member) array[0];
                    Boolean isScrapped = (Boolean) array[1];
                    return storyConverter.convertToMemberThumbnailResponseDto(member, isScrapped);
                })
                .toList();

        return recommendMembers;
    }



    @Transactional
    public void saveStory(StoryRequestDto storyRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();
        Long exhibitionId = storyRequestDto.getExhibitionId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EXHIBITION_NOT_EXISTS));

        Story story = storyConverter.convertToEntity(storyRequestDto, member, exhibition);
        storyRepository.save(story);


//        // 저장한 장르 3개 값을 해당 전시회 장르 카테고리에 1++
//        List<Genre> genreList = Arrays.asList(storyRequestDto.getGenre1(), storyRequestDto.getGenre2(), storyRequestDto.getGenre3());
//        ExhibitionGenre exhibitionGenre = exhibition.getExhibitionGenre();
//
//        genreList.stream().forEach(genre -> {
//            try {
//                Method increaseMethod = ExhibitionGenre.class.getDeclaredMethod("increase" + capitalize(genre) + "Count");
//                increaseMethod.invoke(exhibitionGenre);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        });

    }


    // 테스트용
    @Transactional
    public int getLikeCount(Long storyId) {

        Story story = storyRepository.getById(storyId);

        return story.getStoryLikeCount();
    }


}