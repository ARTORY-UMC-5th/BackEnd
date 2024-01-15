package com.example.demo.domain.member.entity;


import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.exhibition.entity.LikeExhibition;
import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.member.constant.Gender;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.domain.member.constant.Role;
import com.example.demo.global.jwt.dto.JwtTokenDto;
import com.example.demo.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberType memberType;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(length = 200)
    private String password;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(length = 200)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @Builder
    public Member(MemberType memberType, String email, String password, String memberName,
                  String profile, Role role) {
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.profile = profile;
        this.role = role;
    }


    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }


    private String memberUserInfo;


    @Column(nullable = true, length = 10)
    private String nickname;


    private String image;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private String age;


    //@Column(nullable = false, length = 40) 필요하면 제한하자, 일단은 이대로

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Genre genre1;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Genre genre2;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Genre genre3;


    //mypage에서 사용
    private String introduction;
    private String myKeyword;

    //mystory에서 사용
    @Lob
    private String memo;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ScrapMember> scrapMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Story> storyList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ScrapStory> scrapStoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ScrapExhibition> scrapExhibitionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LikeExhibition> likeExhibitionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LikeStory> likeStoryList = new ArrayList<>();
}
