package com.example.demo.domain.story.service;

import com.example.demo.domain.comment.converter.CommentConverter;
import com.example.demo.domain.comment.dto.CommentResponseDto;
import com.example.demo.domain.comment.dto.SubCommentResponseDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.comment.service.SubCommentService;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ExhibitionGenre;
import com.example.demo.domain.exhibition.repository.ExhibitionGenreRepository;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.ScrapMember;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.repository.ScrapMemberRepository;
import com.example.demo.domain.story.constant.State;
import com.example.demo.domain.story.converter.StoryConverter;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.dto.StoryResponseDto;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.domain.story.entity.StoryPicture;
import com.example.demo.domain.story.repository.LikeStoryRepository;
import com.example.demo.domain.story.repository.ScrapStoryRepository;
import com.example.demo.domain.story.repository.StoryPictureRepository;
import com.example.demo.domain.story.repository.StoryRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.BusinessException;

import com.example.demo.global.error.exception.ExhibitionException;
import com.example.demo.global.error.exception.StoryException;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{

    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final StoryRepository storyRepository;
    private final ScrapStoryRepository scrapStoryRepository;
    private final LikeStoryRepository likeStoryRepository;
    private final StoryConverter storyConverter;
    private final ExhibitionGenreRepository exhibitionGenreRepository;
    private final StoryPictureRepository storyPictureRepository;
    private final CommentRepository commentRepository;
    private final SubCommentService subCommentService;
    private final CommentConverter commentConverter;
    private final ScrapMemberRepository scrapMemberRepository;

    @Transactional
    public void saveStory(StoryRequestDto.StoryRequestGeneralDto storyRequestDto, @MemberInfo MemberInfoDto memberInfoDto, Long storyId) {

        Long memberId = memberInfoDto.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
        Long exhibitionId = storyRequestDto.getExhibitionId();
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new ExhibitionException(ErrorCode.EXHIBITION_NOT_EXISTS));

        Story story;
        if (storyId == null) {
            // 새로 story 생성
            story = storyConverter.convertToEntity(storyRequestDto, member, exhibition);
        } else {
            // 기존의 story 가져와 덮어쓰기
            Story existingStory = storyRepository.findById(storyId)
                    .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));
            story = storyConverter.convertToEntityWithStoryId(storyRequestDto, member, exhibition, existingStory, storyId);

            // 임시저장에서 저장한 storyPicture 삭제 -> 후에 다시 저장 예정
            storyPictureRepository.deleteByStoryId(story.getId());
        }

        List<String> picturesUrl = storyRequestDto.getPicturesUrl();
        // 스토리의 사진 저장(repository에 저장)
        List<StoryPicture> storyPictureList = new ArrayList<>();
        for (String pictureUrl : picturesUrl) {
            StoryPicture storyPicture = StoryPicture.builder()
                    .story(story)
                    .pictureUrl(pictureUrl)
                    .build();

            storyPictureList.add(storyPicture);
        }

        // 스토리에 List<StoryPicture> set
        story.setStoryPictureList(storyPictureList);
        // 스토리 썸네일 set
        story.setStoryThumbnailImage(picturesUrl.get(0));

        Genre genre1 = Genre.fromString(storyRequestDto.getGenre1());
        Genre genre2 = Genre.fromString(storyRequestDto.getGenre2());
        Genre genre3 = Genre.fromString(storyRequestDto.getGenre3());

        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibitionId), genre1);
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibitionId), genre2);
        story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibitionId), genre3);

        exhibition.updateCategory();

        storyRepository.save(story);
        storyPictureRepository.saveAll(storyPictureList);
    }

    @Transactional
    /*
     * 1. 해당 스토리가 자신이 쓴건지 체크 -> 아니면 해당 스토리 수정 권한이 없습니다.
     * 2. 자신이 쓴 스토리라면 수정
     */
    public void updateStory(StoryRequestDto.StoryRequestGeneralDto storyRequestDto, Long storyId, MemberInfoDto memberInfoDto) {
        Long memberId = memberInfoDto.getMemberId();

        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));


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

        Genre genre1 = Genre.fromString(storyRequestDto.getGenre1());
        Genre genre2 = Genre.fromString(storyRequestDto.getGenre2());
        Genre genre3 = Genre.fromString(storyRequestDto.getGenre3());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
        Exhibition exhibition = exhibitionRepository.findById(storyRequestDto.getExhibitionId())
                .orElseThrow(() -> new ExhibitionException(ErrorCode.EXHIBITION_NOT_EXISTS));

        // 스토리 생성 (StoryPicture 할당 x)
        Story new_story = story.toBuilder()
                .member(member)
                .exhibition(exhibition)
                .storyTitle(storyRequestDto.getStoryTitle())
                .storySatisfactionLevel(storyRequestDto.getStorySatisfactionLevel())
                .storyWeather(storyRequestDto.getStoryWeather())
                .storyCompanion(storyRequestDto.getStoryCompanion())
                .storyKeyword(storyRequestDto.getStoryKeyword())
                .storyViewingTime(storyRequestDto.getStoryViewingTime())
                .storyContext(storyRequestDto.getStoryContext())
                .genre1(genre1)
                .genre2(genre2)
                .genre3(genre3)
                .isOpen(storyRequestDto.getIsOpen())
                .storyState(State.DONE)
                .year(storyRequestDto.getYear())
                .month(storyRequestDto.getMonth())
                .day(storyRequestDto.getDay())
                .build();


//        List<String> picturesUrl = null;
//        try {
//            picturesUrl = s3Service.saveFileList(storyRequestDto.getPictures());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        List<String> picturesUrl = storyRequestDto.getPicturesUrl();

        List<StoryPicture> storyPictureList = new ArrayList<>();
        for (String pictureUrl : picturesUrl) {
            StoryPicture storyPicture = StoryPicture.builder()
                    .story(new_story)
                    .pictureUrl(pictureUrl)
                    .build();

            storyPictureList.add(storyPicture);
        }

        new_story.setStoryPictureList(storyPictureList);

        new_story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibition.getId()), genre1);
        new_story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibition.getId()), genre2);
        new_story.updateIncreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(exhibition.getId()), genre3);

        /*
          updateCategory : 해당 전시회의 상위 3개 Genre 선택해서 업데이트
         */
        exhibition.updateCategory();

        storyRepository.save(new_story);
        storyPictureRepository.saveAll(storyPictureList);
    }

    // 해당 스토리 id, 열람하는 memberId
    @Transactional
    public StoryResponseDto.StorySpecificResponseDto getStoryById(Long storyId, @MemberInfo MemberInfoDto memberInfoDto) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));
        Long memberId = memberInfoDto.getMemberId();
        if (memberId.equals(story.getMember().getMemberId())) { //본인이면 조회
        }
        else {
            if (!story.getIsOpen() && !memberId.equals(story.getMember().getMemberId())) { //비공개 + 본인이 아니면 조회 불가
                throw new StoryException(ErrorCode.STORY_PRIVATE);
            }
        }

        ScrapStory scrapStory = scrapStoryRepository.findByStoryIdAndMemberId(storyId, memberId);
        Boolean isMemberScrapped = (scrapStory != null && scrapStory.getIsScrapped() != null) ? scrapStory.getIsScrapped() : false;


        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findByStoryId(storyId);
        for (Comment comment : commentList) {
            CommentResponseDto temp = CommentResponseDto.builder()
                    .commentId(comment.getId())
                    .satisfactionLevel(comment.getCommentSatisfactionLevel())
                    .commentContext(comment.getCommentContext())
                    .memberId(comment.getMember().getMemberId())
                    .memberProfile(comment.getMember().getImage())
                    .memberNickname(comment.getMember().getNickname())
                    .build();

            // 각 댓글마다 댓글에 대한 대댓글 리스트 추가 로직
            List<SubCommentResponseDto> subCommentResponseDtoList = subCommentService.findByCommentId(comment.getId());
            CommentResponseDto commentResponseDto = temp.toBuilder()
                    .subCommentResponseDtoList(subCommentResponseDtoList)
                    .build();

            commentResponseDtoList.add(commentResponseDto);
        }

        return storyConverter.convertToSpecificResponseDto(story, isMemberScrapped, commentResponseDtoList);
    }

    @Transactional
    public List<CommentResponseDto> getCommentById(Long storyId) {

        // Story가 없으면 throw
        if (!storyRepository.existsById(storyId)) throw new StoryException(ErrorCode.STORY_NOT_EXISTS);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findByStoryId(storyId);
        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = commentConverter.convertToResponseDto(comment);


            // 각 댓글마다 댓글에 대한 대댓글 리스트 추가 로직
            List<SubCommentResponseDto> subCommentResponseDtoList = subCommentService.findByCommentId(comment.getId());
            commentResponseDto.setSubCommentResponseDtoList(subCommentResponseDtoList);
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
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
                    return StoryConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
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

        List<StoryResponseDto.StoryThumbnailResponseDto> recommendStories = popularStoryPage.getContent()
                .stream()
                .map(array -> {
                    Story story = (Story) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return StoryConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return recommendStories;
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
                    return StoryConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
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
                    return StoryConverter.convertToStoryThumbnailResponseDto(story, isLiked, isScrapped);
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
        Page<Member> recommendMemberPage = memberRepository.recommendMember(pageable, memberId);

        if (recommendMemberPage.getContent().isEmpty()) {
            recommendMemberPage = memberRepository.initRecommendMember(pageable, memberId);
        }


        List<StoryResponseDto.MemberThumbnailResponseDto> recommendMembers = recommendMemberPage.getContent()
                .stream()
                .map((Member member) -> {
                    ScrapMember scrapMember = scrapMemberRepository.findByfromMemberIdAndtoMemberId(memberId, member.getMemberId());
                    if (scrapMember == null) {
                        return StoryConverter.convertToMemberThumbnailResponseDto(member, false);
                    } else {
                        return StoryConverter.convertToMemberThumbnailResponseDto(member, scrapMember.getIsScrapped());
                    }

                })
                .toList();

        return recommendMembers;
    }


    public void updateExhibitionGenreAndCreateIfNotExist(Exhibition exhibition, Genre genre1, Genre genre2, Genre genre3) {
        if (exhibition != null) {
            ExhibitionGenre exhibitionGenre = exhibition.getExhibitionGenre();

            // ExhibitionGenre가 존재하지 않으면 생성
            if (exhibitionGenre == null) {
                exhibitionGenre = ExhibitionGenre.builder()
                        .exhibition(exhibition)
                        .build();
                exhibitionGenreRepository.save(exhibitionGenre);
            }

            // 선택된 장르로 ExhibitionGenre 업데이트
            updateExhibitionGenre(exhibitionGenre, genre1);
            updateExhibitionGenre(exhibitionGenre, genre2);
            updateExhibitionGenre(exhibitionGenre, genre3);
        }
    }

    public void updateExhibitionGenre(ExhibitionGenre exhibitionGenre, Genre genre) {
        if (genre != null) {
            switch (genre) {
                case MEDIA:
                    exhibitionGenre.increaseMediaCount();
                    break;
                case CRAFT:
                    exhibitionGenre.increaseCraftCount();
                    break;
                case DESIGN:
                    exhibitionGenre.increaseDesignCount();
                    break;
                case PICTURE:
                    exhibitionGenre.increasePictureCount();
                    break;
                case SPECIAL_EXHIBITION:
                    exhibitionGenre.increaseSpecialExhibitionCount();
                    break;
                case SCULPTURE:
                    exhibitionGenre.increaseSculptureCount();
                    break;
                case PLAN_EXHIBITION:
                    exhibitionGenre.increasePlanExhibitionCount();
                    break;
                case INSTALLATION_ART:
                    exhibitionGenre.increaseInstallationArtCount();
                    break;
                case PAINTING:
                    exhibitionGenre.increasePaintingCount();
                    break;
                case ARTIST_EXHIBITION:
                    exhibitionGenre.increaseArtistExhibitionCount();
                    break;
            }
        }
    }




    @Transactional
    public void saveStoryNotDate(StoryRequestDto.StoryRequestDateDto storyRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {

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

        // converter에 해당 스토리가 날짜, 전시회만 설정하고 작성 전임을 나타내기 위해 NOT STARTED 상태 추가
        Story story = storyConverter.convertToDateEntity(storyRequestDto, member, exhibition);

        story.initializeNullFields();
        storyRepository.save(story);
    }

    @Transactional
    public void deleteStory(Long storyId, @MemberInfo MemberInfoDto memberInfoDto){
        //자기 스토리 인지 확인 -> 아니면 삭제 권한 없음
        //자기가 쓴 스토리면 삭제
        Optional<Story> optionalStory = storyRepository.findById(storyId);
        if(optionalStory.isPresent()) {
            Story story = optionalStory.get();
            Long memberId = memberInfoDto.getMemberId();
            if (!memberId.equals(story.getMember().getMemberId())) {
                throw new StoryException(ErrorCode.NOT_YOUR_STORY);
            }
            Exhibition exhibition = exhibitionRepository.getById(story.getExhibition().getId());

            // 장르 업데이트 (가중치 감소)
            story.updateDecreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(story.getExhibition().getId()), story.getGenre1());
            story.updateDecreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(story.getExhibition().getId()), story.getGenre2());
            story.updateDecreaseExhibitionGenre(exhibitionGenreRepository.getByExhibitionId(story.getExhibition().getId()), story.getGenre3());
            // exhibition 장르 다시 계산
            exhibition.updateCategory();

            // 스토리의 사진 삭제
            storyPictureRepository.deleteByStoryId(storyId);


            // 스토리 좋아요 삭제
            likeStoryRepository.deleteByStoryId(storyId);

            // 스크랩 스토리, 댓글, 대댓글 삭제 -> 엔티티에서 처리 (스크랩 스토리 삭제되나 확인 필요)
            // 스토리 삭제 -> 댓글과 대댓글 모두 삭제 처리
//            scrapStoryRepository.deleteByMemberIdAndStoryId(memberId, storyId);
            storyRepository.deleteById(storyId);

        }else{
            throw new StoryException(ErrorCode.STORY_NOT_EXISTS);
        }
    }

    @Transactional
    public void draftSaveStoryWithStoryId(StoryRequestDto.StoryRequestGeneralDto draftStoryRequestDto, @MemberInfo MemberInfoDto memberInfoDto, Long storyId) {
        Exhibition exhibition = exhibitionRepository.findById(draftStoryRequestDto.getExhibitionId())
                .orElseThrow(() -> new ExhibitionException(ErrorCode.EXHIBITION_NOT_EXISTS));
        Member member = memberRepository.findById(memberInfoDto.getMemberId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        Story existingStory = storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryException(ErrorCode.STORY_NOT_EXISTS));
        Story story = storyConverter.convertFromDraftToEntityWithStoryId(draftStoryRequestDto, member, exhibition, existingStory, storyId);

        storyPictureRepository.deleteByStoryId(story.getId());

        List<String> picturesUrl = draftStoryRequestDto.getPicturesUrl();
        List<StoryPicture> storyPictureList = new ArrayList<>();
        for (String pictureUrl : picturesUrl) {
            StoryPicture storyPicture = StoryPicture.builder()
                    .story(story)
                    .pictureUrl(pictureUrl)
                    .build();
            storyPictureList.add(storyPicture);
        }

        story.setStoryThumbnailImage(picturesUrl.get(0));
        story.setStoryPictureList(storyPictureList);
        storyRepository.save(story);
    }


    @Transactional
    public void draftSaveStory(StoryRequestDto.StoryRequestGeneralDto draftStoryRequestDto, @MemberInfo MemberInfoDto memberInfoDto) {
        Exhibition exhibition = exhibitionRepository.findById(draftStoryRequestDto.getExhibitionId())
                .orElseThrow(() -> new ExhibitionException(ErrorCode.EXHIBITION_NOT_EXISTS));
        Member member = memberRepository.findById(memberInfoDto.getMemberId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        Story story = storyConverter.convertFromDraftToEntity(draftStoryRequestDto, member, exhibition);

        List<String> picturesUrl = draftStoryRequestDto.getPicturesUrl();
        List<StoryPicture> storyPictureList = new ArrayList<>();
        for (String pictureUrl : picturesUrl) {
            StoryPicture storyPicture = StoryPicture.builder()
                    .story(story)
                    .pictureUrl(pictureUrl)
                    .build();
            storyPictureList.add(storyPicture);
        }

        story.setStoryThumbnailImage(picturesUrl.get(0));
        story.setStoryPictureList(storyPictureList);
        storyRepository.save(story);
    }
}