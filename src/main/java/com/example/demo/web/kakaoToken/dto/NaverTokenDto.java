package com.example.demo.web.kakaoToken.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class NaverTokenDto {

    @Builder @Getter
    public static class Request{
        private String grant_type;
        private String client_id;
        private String redirect_uri;
        private String code;
        private String state;
        private String client_secret;

    }

    @Builder @Getter @ToString
    public static class Response{
        private String token_type;
        private String access_token;
        private Integer expires_in;
        private String refresh_token;
        private Integer error;
        private String error_description;

    }
}
