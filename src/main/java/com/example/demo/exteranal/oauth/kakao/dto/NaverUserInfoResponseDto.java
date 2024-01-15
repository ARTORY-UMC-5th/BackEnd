package com.example.demo.exteranal.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NaverUserInfoResponseDto {

    private String id;

    @JsonProperty("response")
    private NaverAccount naverAccount;

    @Getter @Setter
    public static class NaverAccount {
        private String email;
        private String name;
        private String nickname;
        private String gender;
        @JsonProperty("profile_image")
        private String profile_image;



    }

}
