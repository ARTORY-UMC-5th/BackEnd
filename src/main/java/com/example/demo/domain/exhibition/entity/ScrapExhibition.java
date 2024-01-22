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
public class ScrapExhibition extends BaseEntity {
    //My Story 달력에 필요
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrapExhibition_id")
    private Long id;

    private int year;
    private int month;
    private int day;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    private boolean isScrapped=false;

    public void setScrapped(boolean isScrapped) {
        this.isScrapped = isScrapped;
    }


}