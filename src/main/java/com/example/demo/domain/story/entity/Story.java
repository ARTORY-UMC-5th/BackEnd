package com.example.demo.domain.story.entity;

import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ExhibitionGenre;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.story.constant.State;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Story extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;


    private String storyTitle;
    @Setter
    private String storyThumbnailImage;
    private String storySatisfactionLevel;
    private String storyWeather;
    private String storyCompanion;
    private String storyKeyword;
    private String storyViewingTime; //관람시간 ex) 60분

    @Lob
    @Column(length = 1000000)
    private String storyContext; //글


    @Setter
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<StoryPicture> storyPictureList; //사진

    //member가 Story쓸때 장르3개 선택
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Genre genre1;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Genre genre2;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Genre genre3;

    @Builder.Default
    private Boolean isOpen = true; //공개, 비공개 여부

    @Builder.Default
    private int storyLikeCount = 0; // 좋아요 수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<Comment> commentList;


    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<ScrapStory> scrapStoryList;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<LikeStory> likeStoryList;

    // 추가 : 해당 스토리의 상태 작성 전인지, 임시저장인지, 저장 완료인지 구분 (기본값 : 저장 완료(Done))
    @Builder.Default
    private State storyState = null;

    private int year;
    private int month;
    private int day;
    public void initializeNullFields() {
        // 기본적으로 null로 초기화되어야 하는 속성들을 여기에 추가
        this.storyThumbnailImage = null;
        this.storySatisfactionLevel = null;
        this.storyWeather = null;
        this.storyCompanion = null;
        this.storyKeyword = null;
        this.storyViewingTime = null;
        this.storyContext = null;
        this.genre1 = null;
        this.genre2 = null;
        this.genre3 = null;
        this.isOpen = null;

    }
    // ExhibitionGenre를 업데이트하는 메서드
    public void updateIncreaseExhibitionGenre(ExhibitionGenre exhibitionGenre, Genre genre) {
        if (genre != null) {
            switch (genre) {
                case MEDIA:
                    exhibitionGenre.increaseMediaCount();
                    break;
                case CRAFT:
                    exhibitionGenre.increaseCraftCount();
                    break;
                case DESIGN:
                    exhibitionGenre.increaseDesignCount();
                    break;
                case PICTURE:
                    exhibitionGenre.increasePictureCount();
                    break;
                case SPECIAL_EXHIBITION:
                    exhibitionGenre.increaseSpecialExhibitionCount();
                    break;
                case SCULPTURE:
                    exhibitionGenre.increaseSculptureCount();
                    break;
                case PLAN_EXHIBITION:
                    exhibitionGenre.increasePlanExhibitionCount();
                    break;
                case INSTALLATION_ART:
                    exhibitionGenre.increaseInstallationArtCount();
                    break;
                case PAINTING:
                    exhibitionGenre.increasePaintingCount();
                    break;
                case ARTIST_EXHIBITION:
                    exhibitionGenre.increaseArtistExhibitionCount();
                    break;
            }
        }
    }

    public void updateDecreaseExhibitionGenre(ExhibitionGenre exhibitionGenre, Genre genre) {
        if (genre != null) {
            switch (genre) {
                case MEDIA:
                    exhibitionGenre.decreaseMediaCount();
                    break;
                case CRAFT:
                    exhibitionGenre.decreaseCraftCount();
                    break;
                case DESIGN:
                    exhibitionGenre.decreaseDesignCount();
                    break;
                case PICTURE:
                    exhibitionGenre.decreasePictureCount();
                    break;
                case SPECIAL_EXHIBITION:
                    exhibitionGenre.decreaseSpecialExhibitionCount();
                    break;
                case SCULPTURE:
                    exhibitionGenre.decreaseSculptureCount();
                    break;
                case PLAN_EXHIBITION:
                    exhibitionGenre.decreasePlanExhibitionCount();
                    break;
                case INSTALLATION_ART:
                    exhibitionGenre.decreaseInstallationArtCount();
                    break;
                case PAINTING:
                    exhibitionGenre.decreasePaintingCount();
                    break;
                case ARTIST_EXHIBITION:
                    exhibitionGenre.decreaseArtistExhibitionCount();
                    break;
            }
        }
    }
}