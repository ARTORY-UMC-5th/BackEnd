//package com.example.demo.domain.exhibition.service;
//
//import com.example.demo.domain.exhibition.converter.TestExhibitionConverter;
//import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
//import com.example.demo.domain.exhibition.dto.TestExhibitionResponseDto;
//import com.example.demo.domain.exhibition.entity.Exhibition;
//import com.example.demo.domain.exhibition.entity.LikeExhibition;
//import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
//import com.example.demo.domain.exhibition.repository.LikeExhibitionRepository;
//import com.example.demo.domain.exhibition.repository.TestExhibitionRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//@RequiredArgsConstructor
//public class TestExhibitionService {
//
//    private final ExhibitionRepository exhibitionRepository;
//    private final TestExhibitionConverter testExhibitionConverter;
//
//    public List<TestExhibitionResponseDto.ExhibitionGeneralResponseDto> getRecentExhibitions(int page, Long memberId) {
//        int pageSize = 10;
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        Page<LikeExhibition> recentExhibitionsPage = likeExhibitionRepository.findAllByOrderByCreateTimeByDesc(pageable);
//
//        List<TestExhibitionResponseDto.ExhibitionGeneralResponseDto> recentExhibitions = recentExhibitionsPage.getContent()
//                .stream()
//                .map(likeExhibition -> testExhibitionConverter.convertToGeneralDto(likeExhibition))
//                .collect(Collectors.toList());
//
//        return recentExhibitions;
//    }
//}