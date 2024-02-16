package com.example.demo.domain.story.entity;

import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Builder

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class LikeStory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeStory_id")
    private Long id;

    @Builder.Default
    @Setter
    private Boolean isLiked=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;



}