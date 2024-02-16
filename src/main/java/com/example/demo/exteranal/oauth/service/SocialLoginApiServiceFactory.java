package com.example.demo.exteranal.oauth.service;

import com.example.demo.domain.member.constant.MemberType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialLoginApiServices;

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
        SocialLoginApiServiceFactory.socialLoginApiServices = socialLoginApiServices;
    }

    public static SocialLoginApiService getSocialLoginApiService(MemberType memberType) {
        String socialLoginApiServiceBeanName = "";

        if(MemberType.KAKAO.equals(memberType)) {
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }
        else if(MemberType.NAVER.equals(memberType)) {
            socialLoginApiServiceBeanName = "naverLoginApiServiceImpl";
        }
        else if(MemberType.FORM.equals(memberType)) {
            socialLoginApiServiceBeanName = "formLoginApiServiceImpl";
        }
        return socialLoginApiServices.get(socialLoginApiServiceBeanName);
    }

}
