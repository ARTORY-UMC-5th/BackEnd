package com.example.demo.global.config;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;




@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("ARTORY API 문서")
                .version("v1")
                .description(new StringBuilder().append("소셜 로그인 토큰 발급 주소").append("<br />").append("카카오 : https://kauth.kakao.com/oauth/authorize?client_id=ee35f9bdbb7d489738218a16bc693718&redirect_uri=http://localhost:8080/oauth/kakao/callback&response_type=code").append("<br />").append("네이버 : https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=4yrLNCK6RQMeFj95vayh&state=YOUR_RANDOM_STATE&redirect_uri=http://localhost:8080/login/oauth2/code/naver").toString())
                .contact(new Contact().name("JSW"));
        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement securityItem = new SecurityRequirement()
                .addList("Authorization");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization", bearerAuth))
                .addSecurityItem(securityItem)
                .info(info);
    }

    private Info apiInfo() {
        return new Info()
                .title("title")
                .version("1.0.0")
                .description("description");
    }


}