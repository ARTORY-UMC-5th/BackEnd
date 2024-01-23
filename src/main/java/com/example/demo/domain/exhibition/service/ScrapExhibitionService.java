package com.example.demo.domain.exhibition.service;


import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface ScrapExhibitionService {
    void scrapExhibition(@MemberInfo MemberInfoDto memberInfoDto, Long exhibitionId);

    void disScrapExhibition(@MemberInfo MemberInfoDto memberInfoDto, Long exhibitionId);

}