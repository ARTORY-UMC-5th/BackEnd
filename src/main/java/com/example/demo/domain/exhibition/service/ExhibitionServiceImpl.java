package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {


    private ExhibitionRepository exhibitionRepository;

    @Override
    public List<ExhibitionResponseDto.ExhibitionRecentResponseDto> getRecentExhibitions(int page) {
        int pageSize = 10; // 페이지당 전시회 수
        int offset = (page - 1) * pageSize; // 페이지 시작 위치 계산

        List<Exhibition> recentExhibitions = exhibitionRepository.findTopNByOrderByCreatTimeByDesc(offset, pageSize);
        return recentExhibitions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExhibitionResponseDto.ExhibitionRecentResponseDto convertToDto(Exhibition exhibition) {
        return ExhibitionResponseDto.ExhibitionRecentResponseDto.builder()
                .id(exhibition.getId())
                .exhibitionTitle(exhibition.getExhibitionTitle())
                .exhibitionImage(exhibition.getExhibitionImage())
                .build();
    }
}
