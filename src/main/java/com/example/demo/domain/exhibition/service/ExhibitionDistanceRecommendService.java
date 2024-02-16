package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.entity.Exhibition;

import java.util.List;

public interface ExhibitionDistanceRecommendService {
    List<Exhibition> findClosestExhibitions(double userLat, double userLon, List<Exhibition> exhibitions, int page, int pageSize);
    double calculateDistance(double lat1, double lon1, double lat2, double lon2);
}