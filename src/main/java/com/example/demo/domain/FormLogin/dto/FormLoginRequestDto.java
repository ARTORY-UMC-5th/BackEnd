package com.example.demo.domain.FormLogin.dto;

import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.domain.member.constant.Role;
import lombok.Getter;
import lombok.Setter;

public class FormLoginRequestDto {

    @Getter
    @Setter
    public static class SignUpRequestDto {
        private String email;
        private String password;
        private String memberName;
        private MemberType memberType;
        private Role role;
        private String profile;


    }
    @Getter
    @Setter
    public static class SignInRequestDto {
        private String email;
        private String password;

    }

}
