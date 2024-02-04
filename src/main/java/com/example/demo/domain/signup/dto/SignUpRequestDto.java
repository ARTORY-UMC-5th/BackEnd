package com.example.demo.domain.signup.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequestDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String memberName;
    private String phoneNum;
    private String birth;
    private Boolean termsAgreed;
    private Boolean privacyPolicyAgreed;
    private Boolean marketingAgreed;
}

