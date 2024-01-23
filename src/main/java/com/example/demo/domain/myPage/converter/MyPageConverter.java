package com.example.demo.domain.myPage.converter;


import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import org.springframework.stereotype.Component;


@Component
public class MyPageConverter {

    public MyPageResponseDto convertToGeneralDto(Member member){
        MyPageResponseDto dto = MyPageResponseDto.builder()
                .image(member.getImage())
                .nickname(member.getNickname())
                .introduction(member.getIntroduction())
                .myKeyword(member.getMyKeyword())
                .build();
        return dto;
    }



}
