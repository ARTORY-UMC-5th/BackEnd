package com.example.demo.domain.exhibition.service;




public interface LikeExhibitionService {
    void likeExhibition(Long memberId, Long exhibitionId);
    void disLikeExhibition(Long memberId, Long exhibitionId);

}