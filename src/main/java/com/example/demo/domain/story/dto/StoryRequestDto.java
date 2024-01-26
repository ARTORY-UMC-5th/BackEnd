package com.example.demo.domain.story.dto;

import com.example.demo.domain.member.constant.Genre;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoryRequestDto {



    // exhibition 정보
    private Long exhibitionId;

    private String storyTitle;
    private String storySatisfactionLevel;
    private String storyWeather;
    private String storyCompanion;
    private String storyKeyword;
    private String storyViewingTime;

    @Lob
    private String storyContext;

    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    private Boolean isOpen;

    private List<String> picture;


}
