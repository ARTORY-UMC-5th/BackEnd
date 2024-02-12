package com.example.demo.global.config.web;

import com.example.demo.global.interceptor.AdminAuthorizationInterceptor;
import com.example.demo.global.interceptor.AuthenticationInterceptor;
import com.example.demo.global.resolver.memberInfo.MemberInfoArgumentResolver;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;
    private final MemberInfoArgumentResolver memberInfoArgumentResolver;
    //다른 오리진, 즉 포트가 다른 프로그램의 요청을 허용한다.어떤 경로에 대해서 설정할 것인지.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080","http://localhost:3000","https://art-story.site", "https://artory-front-server.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("Authorization", "Content-Type")
                .exposedHeaders("Custom-Header")
                .allowCredentials(true)
                .maxAge(3600);
    }
    //인터셉터 설정, 인증을 위한 인터셉터 설정을 override한다.

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/oauth/login"
                        ,"/api/form/signUp","/api/form/signIn"
                        ,"/swagger-ui/**", "/v3/api-docs/"
                        ,"/api/access-token/issue"
                        ,"/api/logout"
                        ,"/api/kakao"
                        ,"/api/naver"
                        ,"/api/health"
                        ,"/api/exhibitions/main"
                        ,"/api/server/**"
                        ,"api/server/**"
                        ,"/test/**");

        registry.addInterceptor(adminAuthorizationInterceptor)
                .order(2)
                .addPathPatterns("/api/admin/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberInfoArgumentResolver);
    }
}
