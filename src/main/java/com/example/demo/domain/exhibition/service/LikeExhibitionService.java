package com.example.demo.domain.exhibition.service;


import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface LikeExhibitionService {

    void likeExhibition(@MemberInfo MemberInfoDto memberInfoDto, Long exhibitionId);

    void disLikeExhibition(@MemberInfo MemberInfoDto memberInfoDto, Long exhibitionId);

}