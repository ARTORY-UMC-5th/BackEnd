package com.example.demo.domain.member.dto;


import com.example.demo.domain.member.constant.Gender;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder
@Setter
public class MemberInfoSaveDto {

    @Getter @Setter
    public static class MemberInfo{
        @Schema(description = "본명", defaultValue = "이름 없음", allowableValues = {"홍길동"})
        private String memberName;
        @Schema(description = "소셜 서비스 프로필 사진")
        private String profile;
        @Schema(description = "ARTORY 닉네임", defaultValue = "토리", allowableValues = {"그림 그리는 어피치"})
        private String nickname;
        @Schema(description = "ARTORY 프로필 사진")
        private String image;
        @Schema(description = "성별 요즘 말이 많아서 String입니다.", defaultValue = "NONE", allowableValues = {"MALE", "FEMALE"})
        private Gender gender;
        @Schema(description = "나이 int", defaultValue = "20", allowableValues = {"19", "29"})
        private Integer age;
        private Genre genre1;
        private Genre genre2;
        private Genre genre3;
    }
    @Getter @Setter
    public static class MemberNickname{
        private String nickname;
        private String image;
    }
    @Getter @Setter
    public static class MemberAgeAndGender{
        private Gender gender;
        private Integer age;
    }
    @Getter @Setter
    public static class MemberGenre{

        private Genre genre1;
        private Genre genre2;
        private Genre genre3;
    }





}
