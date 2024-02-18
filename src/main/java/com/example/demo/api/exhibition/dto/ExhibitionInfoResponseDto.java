package com.example.demo.api.exhibition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.DateTime;

import java.util.List;


@Data
public class ExhibitionInfoResponseDto {

    @Data
    public static class ResponseDto {
        @JsonProperty("culturalEventInfo")
        private CulturalEventInfoResponse culturalEventInfoResponse;
    }

    @Data
    public static class CulturalEventInfoResponse {

        @JsonProperty("list_total_count")
        private int list_total_count;

        @JsonProperty("RESULT")
        private Result RESULT;

        @JsonProperty("row")
        private List<ExhibitionInfo> exhibitionInfoList;
//        private List<ExhibitionInfo> exhibitionInfoList;

    }

    @Data
    public static class Result {
        private String CODE;
        private String MESSAGE;

    }

    @Data
    public static class ExhibitionInfo {

        @JsonProperty("CODENAME")
        private String codename;


        //GUNAME
        @JsonProperty("GUNAME")
        private String exhibition_address;

        //DATE
        @JsonProperty("DATE")
        private String exhibition_duration;

        // MAIN_IMG
        @JsonProperty("MAIN_IMG")
        private String exhibition_image;

        // ORG_NAME
        @JsonProperty("ORG_NAME")
        private String exhibition_institution;

        // LAT
        @JsonProperty("LAT")
        private String exhibition_latitude;

        // LOT
        @JsonProperty("LOT")
        private String exhibition_longitude;

        // PLACE
        @JsonProperty("PLACE")
        private String exhibition_place;

        // USE_FEE
        @JsonProperty("USE_FEE")
        private String exhibition_price;

        // TITLE
        @JsonProperty("TITLE")
        private String exhibition_title;

        // ORG_LINK
        @JsonProperty("ORG_LINK")
        private String exhibition_url;

        // USE_TRGT
        @JsonProperty("USE_TRGT")
        private String exhibition_viewing_age;

        @JsonProperty("STRTDATE")
        private String start_date;

        @JsonProperty("END_DATE")
        private String end_date;


    }
}
