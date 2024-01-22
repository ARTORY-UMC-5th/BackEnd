package com.example.demo.domain.exhibition.service;


import com.example.demo.domain.exhibition.converter.ExhibitionConverter;
import com.example.demo.domain.exhibition.dto.ExhibitionRequestDto;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionConverter exhibitionConverter;
    private final ExhibitionDistanceRecommendService exhibitionDistanceRecommendService;




//    @Override
//    public ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(Long memberId, int page, ExhibitionRequestDto requestDto) {
//        ExhibitionResponseDto.ExhibitionListResponseDto allResponseDto = new ExhibitionResponseDto.ExhibitionListResponseDto();
//        // 최근 전시회 가져오기
//        allResponseDto.setRecentExhibitionDtoList(getRecentExhibitions(memberId,page));
//
//        // 인기 있는 전시회 가져오기
//        allResponseDto.setPopluarExhibitionDtoList(getPopularityExhibitions(memberId,page));
//
//        // 거리 추천된 전시회 가져오기
//        allResponseDto.setDistanceRecommendExhibitionDtoList(getDistanceRecommendExhibitions(requestDto,memberId, page));
//
//        // 랜덤 전시회 가져오기
//        allResponseDto.setRandomExhibitionDtoList(getRandomExhibitions(memberId, page));
//
////        // 비슷한 전시회 가져오기
////        allResponseDto.setRecommendExhibitionDtoList(getRecommendExhibitions(memberId,page));
////        // 추천 전시회 가져오기
////        allResponseDto.setSimilarExhibitionDtoList(getSimilarExhibitions(memberId,page));
//
//
//        return allResponseDto;
//    }


    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto, Long memberId, int page) {
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
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(Long memberId, LocalDate currentDate, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        Page<Object[]> recentExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(memberId, pageable);

        Page<Object[]> recentExhibitionsPage = exhibitionRepository.findActiveExhibitions(memberId,currentDate,pageable);


        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions = recentExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[1];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked,isScrapped);
                })
                .collect(Collectors.toList());


        return recentExhibitions;
    }


    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(Long memberId, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> likeExhibitionsPage = exhibitionRepository.findAllByOrderByExhibitionLikeCountDesc(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> likeExhibitions = likeExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[1];
                    return exhibitionConverter.convertToGeneralDto(exhibition,isLiked,isScrapped);
                })
                .collect(Collectors.toList());


        return likeExhibitions;
    }



    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, Long memberId, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> searchResultsPage = exhibitionRepository.findByExhibitionTitleContainingCase(memberId, title, pageable);


        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchResults = searchResultsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[1];
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
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(Long memberId, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> randomExhibitionsPage = exhibitionRepository.findRandomExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> randomExhibitions = randomExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped =  (Boolean) array[1];
                    return exhibitionConverter.convertToGeneralDto(exhibition,isLiked,isScrapped);
                })
                .collect(Collectors.toList());


        return randomExhibitions;
    }

//
//    @Override
//    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(int page) {
//        int pageSize = 10;
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        Page<Exhibition> recommendExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);
//
//        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recommendExhibitions = recommendExhibitionsPage.getContent()
//                .stream()
//                .map(exhibitionConverter::convertToGeneralDto)
//                .collect(Collectors.toList());
//
//
//        return recommendExhibitions;
//    }
//
//    @Override
//    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(int page) {
//        int pageSize = 10;
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        Page<Exhibition> similarExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);
//
//        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> similarExhibitions = similarExhibitionsPage.getContent()
//                .stream()
//                .map(exhibitionConverter::convertToGeneralDto)
//                .collect(Collectors.toList());
//
//
//        return similarExhibitions;
//    }





}