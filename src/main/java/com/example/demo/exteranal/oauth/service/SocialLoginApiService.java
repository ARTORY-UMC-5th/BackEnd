package com.example.demo.exteranal.oauth.service;


import com.example.demo.exteranal.oauth.model.OAuthAttributes;

public interface SocialLoginApiService {

    OAuthAttributes getUserInfo(String accessToken);

}
