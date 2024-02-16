package com.example.demo.domain.myStory.dto;

import com.example.demo.domain.member.constant.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;


public class MyStoryRequestDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateInfoRequestDto {
        private int year;
        private int month;
        private int day;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberInfoRequestDto {
        @Lob
        @Column(length = 1000000)
        private String memo;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateAndTitleRequestDto {
        private int year;
        private int month;
        private int day;
        private String exhibitionTitle;

    }



}
