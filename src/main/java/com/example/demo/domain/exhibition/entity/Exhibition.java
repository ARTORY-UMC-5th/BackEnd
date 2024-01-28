package com.example.demo.domain.exhibition.entity;



import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.story.entity.Story;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Exhibition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Long id;

    private String exhibitionTitle; //제목, 크롤링기준: TITLE
    private String exhibitionImage; //이미지 Url, 크롤링기준: MAIN_IMG
    private String exhibitionAddress; //자치구, 크롤링기준: GUNAME
    private String exhibitionPlace; //장소, 크롤링기준: PLACE
    private String exhibitionDuration; //기간, 크롤링기준: DATE
    private String exhibitionInstitution; //기관명, 크롤링기준: ORG_NAME
    private String exhibitionViewingTime; //관람시간, 유저가 입력

    private String exhibitionViewingAge; //관람연령, 크롤링기준: USE_TRGT
    private String exhibitionPrice; //가격, 크롤링기준: USE_FEE

    @Column(length = 800)
    private String exhibitionUrl; //전시회 사이트, 크롤링 기준: ORG_LINK
    private String exhibitionLongitude; //경도, 크롤링기준: LOT
    private String exhibitionLatitude; //위도, 크롤링기준: LAT

    private String exhibitionStartDate; //시작날짜, 크롤링 기준: STRTDATE
    private String exhibitionEndDate; //종료날짜, 크롤링 기준: END_DATE

    @Builder.Default
    private boolean isEnded = false; //전시회 종료되었는지, 안되었는지

    @Builder.Default
    private boolean isStarted = false; //전시회 시작되었는지, 안되었는지

    @Setter
    @Builder.Default
    private int exhibitionLikeCount = 0; // 좋아요 수

    private String genreCategory1;
    private String genreCategory2;
    private String genreCategory3;


    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Story> storyList;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScrapExhibition> scrapExhibitionList;

    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeExhibition> likeExhibitionList;

    @OneToOne(mappedBy = "exhibition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExhibitionGenre exhibitionGenre;


    //가장 높은 3개의 값을 가지는 장르 선택
    public void updateCategory() {
        Map<String, Integer> genreCounts = Map.of(
                "media", exhibitionGenre.getMedia(),
                "craft", exhibitionGenre.getCraft(),
                "design", exhibitionGenre.getDesign(),
                "picture", exhibitionGenre.getPicture(),
                "specialExhibition", exhibitionGenre.getSpecialExhibition(),
                "sculpture", exhibitionGenre.getSculpture(),
                "planExhibition", exhibitionGenre.getPlanExhibition(),
                "installationArt", exhibitionGenre.getInstallationArt(),
                "painting", exhibitionGenre.getPainting(),
                "artistExhibition", exhibitionGenre.getArtistExhibition()
        );

        List<String> topGenres = genreCounts.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .filter(entry -> entry.getValue() > 0)
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("topGenres = " + topGenres);
        // 1개 이상이면, Genre1 추가
        this.genreCategory1 = topGenres.size() > 0 ? topGenres.get(0) : null;

        // 2개 이상이면, Genre2 추가
        this.genreCategory2 = topGenres.size() > 1 ? topGenres.get(1) : null;

        // 3개 이상이면, Genre3 추가
        this.genreCategory3 = topGenres.size() > 2 ? topGenres.get(2) : null;

    }
}