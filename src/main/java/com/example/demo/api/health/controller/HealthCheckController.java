package com.example.demo.api.health.controller;

import com.example.demo.api.health.dto.HealthCheckResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
@Tag(name = "서버 기동중 확인", description = "배포시 확인용 api")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {
    private final Environment environment;

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> healthCheck(){
        HealthCheckResponseDto healthCheckResponseDto = HealthCheckResponseDto.builder()
                .health("ok")
                .activeProfiles(Arrays.asList(environment.getActiveProfiles()))
                .build();
        return ResponseEntity.ok(healthCheckResponseDto);
    }
}
