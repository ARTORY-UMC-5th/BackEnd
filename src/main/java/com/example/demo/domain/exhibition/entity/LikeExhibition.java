package com.example.demo.domain.exhibition.entity;


import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class LikeExhibition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeExhibition_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    private String exhibitionTitle; //제목, 크롤링기준: TITLE
    private String exhibitionImage; //이미지 Url, 크롤링기준: MAIN_IMG

    private boolean isLiked = false; //전시회 좋아하는지


    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }


}