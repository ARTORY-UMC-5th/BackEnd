package com.example.demo.domain.exhibition.service;


import com.example.demo.domain.exhibition.converter.ExhibitionConverter;
import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionConverter exhibitionConverter;
    private final ExhibitionDistanceRecommendService exhibitionDistanceRecommendService;
    private final MemberService memberService;



    @Override
    public ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate,int page, ExhibitionRequestDto requestDto) {
        Long memberId = memberInfoDto.getMemberId();

        ExhibitionResponseDto.ExhibitionListResponseDto allResponseDto = new ExhibitionResponseDto.ExhibitionListResponseDto();
        // 최근 전시회 가져오기
        allResponseDto.setRecentExhibitionDtoList(getRecentExhibitions(memberInfoDto,currentDate,page));
        // 인기 있는 전시회 가져오기
        allResponseDto.setPopluarExhibitionDtoList(getPopularityExhibitions(memberInfoDto,page));

        // 거리 추천된 전시회 가져오기
        allResponseDto.setDistanceRecommendExhibitionDtoList(getDistanceRecommendExhibitions(requestDto,memberInfoDto, page));

        // 랜덤 전시회 가져오기
        allResponseDto.setRandomExhibitionDtoList(getRandomExhibitions(memberInfoDto, page));

//        // 비슷한 전시회 가져오기
//        allResponseDto.setRecommendExhibitionDtoList(getRecommendExhibitions(memberId,page));
//        // 추천 전시회 가져오기
//        allResponseDto.setSimilarExhibitionDtoList(getSimilarExhibitions(memberId,page));


        return allResponseDto;
    }


    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto,@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        double userLatitude = Double.parseDouble(requestDto.getLatitude());
        double userLongitude = Double.parseDouble(requestDto.getLongitude());

        // 거리 기반으로 가장 가까운 전시회 가져오기
        List<Exhibition> closestExhibitions = exhibitionDistanceRecommendService.findClosestExhibitions(userLatitude, userLongitude, getAllExhibitions(), page, pageSize);

        // 좋아요 여부 가져오기
        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> result = closestExhibitions.stream()
                .map(exhibition -> {
                    Boolean isLiked = exhibitionRepository.findLikeStatusByMemberIdAndExhibitionId(memberId, exhibition.getId());
                    Boolean isScrapped =  exhibitionRepository.findScrapStatusByMemberIdAndExhibitionId(memberId, exhibition.getId());
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked,isScrapped);
                })
                .collect(Collectors.toList());

        return result;
    }



    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(@MemberInfo MemberInfoDto memberInfoDto, LocalDate currentDate, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<Object[]> recentExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(memberId,currentDate,pageable);


        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions = recentExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked,isScrapped);
                })
                .collect(Collectors.toList());


        return recentExhibitions;
    }


    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> likeExhibitionsPage = exhibitionRepository.findAllByOrderByExhibitionLikeCountDesc(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> likeExhibitions = likeExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition,isLiked,isScrapped);
                })
                .collect(Collectors.toList());


        return likeExhibitions;
    }



    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, @MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> searchResultsPage = exhibitionRepository.findByExhibitionTitleContainingCase(memberId, title, pageable);


        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchResults = searchResultsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition,isLiked,isScrapped);
                })
                .collect(Collectors.toList());

        return searchResults;
    }






    @Override
    public ExhibitionResponseDto.ExhibitionSpecificResponseDto getExhibitionById(Long id) {
        Exhibition exhibition = exhibitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exhibition not found with id: " + id));

        return exhibitionConverter.convertToSpecificDto(exhibition);
    }


    // 모든 전시회 정보 조회 메서드
    @Override
    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> randomExhibitionsPage = exhibitionRepository.findRandomExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> randomExhibitions = randomExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition,isLiked,isScrapped);
                })
                .collect(Collectors.toList());


        return randomExhibitions;
    }



    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // 각 멤버의 genre1, genre2, genre3 값 가져오기
        Member member = memberService.findMemberByMemberId(memberId);
        Genre genre1 = member.getGenre1();
        Genre genre2 = member.getGenre2();
        Genre genre3 = member.getGenre3();
        // genre1, genre2, genre3를 문자열로 변환
        String genre1String = genre1.name();
        String genre2String = genre2.name();
        String genre3String = genre3.name();

        System.out.println("genre1String: " + genre1String);
        System.out.println("genre2String: " + genre2String);
        System.out.println("genre3String: " + genre3String);

        Page<Object[]> recommendExhibitionsPage = exhibitionRepository.findRecommendedExhibitions(memberId, genre1String, genre2String, genre3String, pageable);
        System.out.println("Total elements: " + recommendExhibitionsPage.getTotalElements()); // 전체 결과 개수
        System.out.println("Number of elements: " + recommendExhibitionsPage.getNumberOfElements()); // 현재 페이지의 결과 개수
        System.out.println("Content: " + recommendExhibitionsPage.getContent()); // 페이지 내용

        return recommendExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> similarExhibitionsPage = exhibitionRepository.findRandomExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> similarExhibitions = similarExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition,isLiked,isScrapped);
                })
                .collect(Collectors.toList());

        return similarExhibitions;
    }





}