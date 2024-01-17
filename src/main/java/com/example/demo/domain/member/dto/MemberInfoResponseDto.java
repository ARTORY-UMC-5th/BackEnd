package com.example.demo.domain.member.dto;


import com.example.demo.domain.member.constant.Gender;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.constant.Role;
import com.example.demo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberInfoResponseDto {

    private Long memberId;
    private String email;
    private String memberName;
    private String profile;
    private String nickname;
    private String image;
    private Gender gender;
    private Integer age;
    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    public static MemberInfoResponseDto of(Member member) {
        return MemberInfoResponseDto.builder()
                .memberId(member.getMemberId())
                .age(member.getAge())
                .nickname(member.getNickname())
                .image(member.getImage())
                .gender(member.getGender())
                .genre1(member.getGenre1())
                .genre2(member.getGenre2())
                .genre3(member.getGenre3())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .profile(member.getProfile())
                .build();
    }
}
