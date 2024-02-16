package com.example.demo.exteranal.oauth.kakao.service;

import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.exteranal.oauth.kakao.client.KakaoUserInfoClient;
import com.example.demo.exteranal.oauth.kakao.client.NaverUserInfoClient;
import com.example.demo.exteranal.oauth.kakao.dto.KakaoUserInfoResponseDto;
import com.example.demo.exteranal.oauth.kakao.dto.NaverUserInfoResponseDto;
import com.example.demo.exteranal.oauth.model.OAuthAttributes;
import com.example.demo.exteranal.oauth.service.SocialLoginApiService;
import com.example.demo.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NaverLoginApiServiceImpl implements SocialLoginApiService {

    private final NaverUserInfoClient naverUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf8";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        NaverUserInfoResponseDto naverUserInfoResponseDto = naverUserInfoClient.getNaverUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken);
        System.out.println("naverrrrr : " + naverUserInfoResponseDto.getNaverAccount());
        NaverUserInfoResponseDto.NaverAccount Account = naverUserInfoResponseDto.getNaverAccount();
        String email = Account.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? naverUserInfoResponseDto.getId() : email)//이메일 없으면 카카오 아이디 저장
                .nickname(Account.getNickname())
                .name(Account.getName())
                .gender(Account.getGender())
                .profile(Account.getProfile_image())
                .memberType(MemberType.NAVER)
                .build();
    }

}
