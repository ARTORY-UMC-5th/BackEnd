package com.example.demo.api.exhibition.util;

import com.example.demo.api.exhibition.dto.ExhibitionInfoResponseDto;
import com.example.demo.domain.exhibition.converter.ExhibitionConverter;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class ExhibitionInfoUtil {

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionConverter exhibitionConverter;

    @Value("${exhibition.apikey}")
    private String apiKey;

    public void getInfo() {

        String url = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/culturalEventInfo/1/300/";

        RestTemplate restTemplate = new RestTemplate();

        // GET 요청
        ResponseEntity<ExhibitionInfoResponseDto.ResponseDto> responseEntity = restTemplate.getForEntity(url, ExhibitionInfoResponseDto.ResponseDto.class);


        ExhibitionInfoResponseDto.CulturalEventInfoResponse culturalEventInfoResponse = responseEntity.getBody().getCulturalEventInfoResponse();


        List<ExhibitionInfoResponseDto.ExhibitionInfo> exhibitionInfoList = culturalEventInfoResponse.getExhibitionInfoList();

        List<Exhibition> exhibitionList = new ArrayList<>();

        for (ExhibitionInfoResponseDto.ExhibitionInfo exhibitionInfo : exhibitionInfoList) {

            // 전시/미술 카테고리가 아닐 경우 pass
            if (!Objects.equals(exhibitionInfo.getCodename(), "전시/미술")) continue;

            //같은 전시회가 존재하면 pass
            Boolean isExist = exhibitionRepository.existsByExhibitionTitle(exhibitionInfo.getExhibition_title());
            if (isExist) continue;

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String current_date = currentDateTime.format(formatter);

            // 하루 전 날짜
            LocalDateTime nextDateTime = currentDateTime.minusDays(1);
            String next_date_str = nextDateTime.format(formatter);

            // 날짜 부분만 추출
            String exhibition_start_date = exhibitionInfo.getStart_date().substring(0, 10); // 처음부터 10글자까지
            String exhibition_end_date = exhibitionInfo.getEnd_date().substring(0, 10); // 처음부터 10글자까지

            int exhibition_like_count = 0;
            boolean is_ended = false;

            // is_ended 계산
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate nextDate = LocalDate.parse(next_date_str.substring(0, 10), dateFormatter);
            LocalDate exhibitionEndDate = LocalDate.parse(exhibition_end_date, dateFormatter);
            is_ended = nextDate.isAfter(exhibitionEndDate);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            currentDateTime = LocalDateTime.parse(current_date, timeFormatter);
            LocalDateTime exhibitionStartDate = LocalDateTime.parse(exhibition_start_date + " 00:00:00", timeFormatter);
            boolean is_started = currentDateTime.isAfter(exhibitionStartDate);

            Exhibition exhibition = exhibitionConverter.convertToEntity(exhibitionInfo, exhibition_like_count, is_started, is_ended, "0분");
            exhibitionList.add(exhibition);
        }

        exhibitionRepository.saveAll(exhibitionList);
    }
}
