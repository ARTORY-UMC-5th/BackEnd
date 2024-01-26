package com.example.demo.domain.story.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ExhibitionGenre;
import com.example.demo.domain.exhibition.repository.ExhibitionGenreRepository;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.story.converter.StoryConverter;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.entity.StoryPicture;
import com.example.demo.domain.story.repository.ScrapStoryRepository;
import com.example.demo.domain.story.repository.StoryPictureRepository;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.EntityNotFoundException;

import com.example.demo.global.error.exception.StoryException;
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
import java.util.ArrayList;
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
    private final ExhibitionGenreRepository exhibitionGenreRepository;
    private final StoryPictureRepository storyPictureRepository;

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


    @Transactional
    public StoryResponseDto.StoryListResponseDto getAllStoryList(int page,  @MemberInfo MemberInfoDto memberInfoDto) {

        StoryResponseDto.StoryListResponseDto allStoryDto = StoryResponseDto.StoryListResponseDto.builder()
                .poluarStoryDtoList(getPopularStories(page, memberInfoDto))
                .recommendStoryDtoList(getRecommendStories(page, memberInfoDto))
                .recentStoryDtoList(getRecentStories(page, memberInfoDto))
                .recommendMemberDtoList(getRecommendMembers(page, memberInfoDto))
                .build();

        return allStoryDto;
    }


    @Transactional
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


    @Transactional
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


    @Transactional
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



    @Transactional
    public List<StoryResponseDto.MemberThumbnailResponseDto> getRecommendMembers(int page,  @MemberInfo MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        // 추후 구현
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> recommendMemberPage = memberRepository.recommendMember(pageable, memberId);

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


        // 스토리 저장 전에 스토리-전시회에 해당하는 ExhibitionGenre 있는지 확인

        // ExhibitionGenre가 있으면 pass, 없으면 만들어서 전시회와 매핑

        // 스토리의 category1, 2, 3을 해당 전시회 ExhibitionGenre에 1 증가시키고 -> 전시회 category 업데이트

        // 후 스토리 저장

        Long memberId = memberInfoDto.getMemberId();
        Member member = memberRepository.getById(memberId);
        Long exhibitionId = storyRequestDto.getExhibitionId();
        Exhibition exhibition = exhibitionRepository.getById(exhibitionId);


        Boolean isExisted = exhibitionGenreRepository.existsByExhibitionId(exhibitionId);

        // 테이블이 존재하면, 패스
        if (isExisted == null) {
            ExhibitionGenre exhibitionGenre = ExhibitionGenre.builder()
                    .exhibition(exhibitionRepository.getById(exhibitionId))
                    .build();

            exhibitionGenreRepository.save(exhibitionGenre);
        }


        // 스토리로 변환, 이때 List<StoryPicture>는 null값
        Story story = storyConverter.convertToEntity(storyRequestDto, member, exhibition);


        // 스토리의 사진 저장(repository에 저장)
        List<StoryPicture> storyPictureList = new ArrayList<>();
        for (String pictureUrl : storyRequestDto.getPictures()) {
            StoryPicture storyPicture = StoryPicture.builder()
                    .story(story)
                    .pictureUrl(pictureUrl)
                    .build();

            storyPictureList.add(storyPicture);
        }

        // 스토리에 List<StoryPicture> set
        story.setStoryPictureList(storyPictureList);


        /**
         * 스토리에서 선택한 장르가 3개가 아닐수도 있음
         * story -> 해당 exhibitionGenre를 1 증가 (updateExhibitionGenre) 장르가 null이면, pass
         */
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibitionId), storyRequestDto.getGenre1());
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibitionId), storyRequestDto.getGenre2());
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibitionId), storyRequestDto.getGenre3());

        /**
         * updateCategory : 해당 전시회의 상위 3개 Genre 선택해서 업데이트
         */
        exhibition.updateCategory();

        /**
         * Story, 해당 Story의 사진 모음 저장
         */
        storyRepository.save(story);
        storyPictureRepository.saveAll(storyPictureList);
    }



    @Transactional
    /**
     * 1. 해당 스토리가 자신이 쓴건지 체크 -> 아니면 해당 스토리 수정 권한이 없습니다.
     * 2. 자신이 쓴 스토리라면 수정
     */
    public void updateStory(StoryRequestDto storyRequestDto, Long storyId, MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        Optional<Story> optionalStory = storyRepository.findById(storyId);

        if (optionalStory.isEmpty()) {
            throw new StoryException(ErrorCode.STORY_NOT_EXISTS);
        }


        Story story = optionalStory.get();

        // 장르 수정을 위해 설정되어있는 장르를 -1 업데이트
        story.updateDecreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(storyRequestDto.getExhibitionId()), story.getGenre1());
        story.updateDecreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(storyRequestDto.getExhibitionId()), story.getGenre2());
        story.updateDecreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(storyRequestDto.getExhibitionId()), story.getGenre3());

        // 장르 수정을 위해 기존의 사진 삭제
        storyPictureRepository.deleteByStoryId(storyId);


        // 해당 스토리가 자신이 쓴 스토리가 아니라면 예외 발생
        if (!memberId.equals(story.getMember().getMemberId())) {
            throw new StoryException(ErrorCode.NOT_YOUR_STORY);
        }

        // 스토리 생성 (StoryPicture 할당 x)
        story = Story.builder()
                .id(storyId)
                .member(memberRepository.getById(memberInfoDto.getMemberId()))
                .exhibition(exhibitionRepository.getById(storyRequestDto.getExhibitionId()))
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
                .build();


        List<StoryPicture> storyPictureList = new ArrayList<>();
        for (String pictureUrl : storyRequestDto.getPictures()) {
            StoryPicture storyPicture = StoryPicture.builder()
                    .story(story)
                    .pictureUrl(pictureUrl)
                    .build();

            storyPictureList.add(storyPicture);
        }

        story.setStoryPictureList(storyPictureList);

        Exhibition exhibition = story.getExhibition();
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibition.getId()), storyRequestDto.getGenre1());
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibition.getId()), storyRequestDto.getGenre2());
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibition.getId()), storyRequestDto.getGenre3());

        /**
         * updateCategory : 해당 전시회의 상위 3개 Genre 선택해서 업데이트
         */
        exhibition.updateCategory();

        storyRepository.save(story);
        storyPictureRepository.saveAll(storyPictureList);
    }

}