package com.example.demo.domain.exhibition.service;


import com.example.demo.domain.exhibition.converter.ExhibitionConverter;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExhibitionDistanceRecommendServiceImpl implements ExhibitionDistanceRecommendService {

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionConverter exhibitionConverter;


    // 지구의 반지름 (단위: km)
    private static final double EARTH_RADIUS = 6371.0;

    // 멤버의 위도, 경도를 기반으로 가장 가까운 전시회를 찾는 메서드
    @Override
    public List<Exhibition> findClosestExhibitions(double userLat, double userLon, List<Exhibition> exhibitions, int page, int pageSize) {
        List<Exhibition> sortedExhibitions = exhibitions.stream()
                .sorted(Comparator.comparingDouble(exhibition ->
                        calculateDistance(userLat, userLon,
                                Double.parseDouble(exhibition.getExhibitionLatitude()),
                                Double.parseDouble(exhibition.getExhibitionLongitude()))))
                .collect(Collectors.toList());

        int startIdx = (page - 1) * pageSize;
        int endIdx = Math.min(startIdx + pageSize, sortedExhibitions.size());

        return sortedExhibitions.subList(startIdx, endIdx);
    }


    // Haversine 공식을 사용하여 두 지점 간의 거리를 계산하는 메서드
    @Override
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }







}

