package com.example.demo.domain.exhibition.service;




public interface ScrapExhibitionService {
    void scrapExhibition(Long memberId, Long exhibitionId);

    void disScrapExhibition(Long memberId, Long exhibitionId);

}
