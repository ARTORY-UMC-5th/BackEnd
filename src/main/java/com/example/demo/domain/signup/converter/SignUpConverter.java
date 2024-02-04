package com.example.demo.domain.signup.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.myPage.dto.MyPageResponseDto;
import com.example.demo.domain.signup.dto.SignUpRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SignUpConverter {
    public SignUpRequestDto convertToGeneralDto(Member member) {
        SignUpRequestDto dto = SignUpRequestDto.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .confirmPassword(member.getConfirmPassword())
                .memberName(member.getMemberName())
                .phoneNum(member.getPhoneNum())
                .birth(member.getBirth())
                .build();
        return dto;
    }
}
