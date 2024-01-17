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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionConverter exhibitionConverter;
    private final ExhibitionDistanceRecommendService exhibitionDistanceRecommendService;




    @Override
    public ExhibitionResponseDto.ExhibitionListResponseDto getAllExhibitionList(int page, ExhibitionRequestDto requestDto) {
        ExhibitionResponseDto.ExhibitionListResponseDto allResponseDto = new ExhibitionResponseDto.ExhibitionListResponseDto();

        // 최근 전시회 가져오기
        allResponseDto.setRecentExhibitionDtoList(getRecentExhibitions(page));

        // 인기 있는 전시회 가져오기
        allResponseDto.setPopluarExhibitionDtoList(getPopularityExhibitions(page));

        // 거리 추천된 전시회 가져오기
        allResponseDto.setDistanceRecommendExhibitionDtoList(getDistanceRecommendExhibitions(requestDto, page));

        // 랜덤 전시회 가져오기
        allResponseDto.setRandomExhibitionDtoList(getRandomExhibitions(page));

        // 비슷한 전시회 가져오기
        allResponseDto.setRecommendExhibitionDtoList(getRecommendExhibitions(page));
        // 추천 전시회 가져오기
        allResponseDto.setSimilarExhibitionDtoList(getSimilarExhibitions(page));


        return allResponseDto;
    }
    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDistanceRecommendExhibitions(ExhibitionRequestDto requestDto, int page) {
        int pageSize = 10;
        double userLatitude = Double.parseDouble(requestDto.getLatitude());
        double userLongitude = Double.parseDouble(requestDto.getLongitude());

        List<Exhibition> allExhibitions = getAllExhibitions();
        List<Exhibition> closestExhibitions = exhibitionDistanceRecommendService.findClosestExhibitions(userLatitude, userLongitude, allExhibitions, page, pageSize);

        return closestExhibitions.stream()
                .map(exhibitionConverter::convertToGeneralDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Exhibition> recentExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions = recentExhibitionsPage.getContent()
                .stream()
                .map(exhibitionConverter::convertToGeneralDto)
                .collect(Collectors.toList());


        return recentExhibitions;
    }
    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPopularityExhibitions(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Exhibition> likeExhibitionsPage = exhibitionRepository.findAllByOrderByExhibitionLikeCountDesc(pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> likeExhibitions = likeExhibitionsPage.getContent()
                .stream()
                .map(exhibitionConverter::convertToGeneralDto)
                .collect(Collectors.toList());


        return likeExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchExhibitionsByTitle(String title, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Exhibition> searchResultsPage = exhibitionRepository.findByExhibitionTitleContainingCase(title, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> searchResults = searchResultsPage.getContent()
                .stream()
                .map(exhibitionConverter::convertToGeneralDto)
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
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRandomExhibitions(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Exhibition> randomExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> randomExhibitions = randomExhibitionsPage.getContent()
                .stream()
                .map(exhibitionConverter::convertToGeneralDto)
                .collect(Collectors.toList());


        return randomExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecommendExhibitions(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Exhibition> recommendExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> recommendExhibitions = recommendExhibitionsPage.getContent()
                .stream()
                .map(exhibitionConverter::convertToGeneralDto)
                .collect(Collectors.toList());


        return recommendExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSimilarExhibitions(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Exhibition> similarExhibitionsPage = exhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> similarExhibitions = similarExhibitionsPage.getContent()
                .stream()
                .map(exhibitionConverter::convertToGeneralDto)
                .collect(Collectors.toList());


        return similarExhibitions;
    }
}