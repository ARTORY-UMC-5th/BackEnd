package com.example.demo.domain.member.entity;


import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.exhibition.entity.LikeExhibition;
import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.member.constant.Gender;
import com.example.demo.domain.member.constant.Genre;
import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.domain.member.constant.Role;
import com.example.demo.domain.member.dto.MemberInfoSaveDto;
import com.example.demo.domain.story.entity.LikeStory;
import com.example.demo.domain.story.entity.ScrapStory;
import com.example.demo.domain.story.entity.Story;
import com.example.demo.global.jwt.dto.JwtTokenDto;
import com.example.demo.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
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

    @Builder
    public Member(MemberInfoSaveDto memberInfosaveDto) {
        super();
    }


    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }


//    private String memberUserInfo;

    @Setter
    @Column(nullable = true, length = 10)
    private String nickname;

    @Setter
    private String image;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private Integer age;


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
    @Setter
    private String introduction;

    @Setter
    private String myKeyword;

    //mystory에서 사용
    @Setter
    @Lob
    @Column(length = 1000000)
    private String memo;

//원재

    @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL)
    private List<ScrapMember> scrapfromMemberList;

    @OneToMany(mappedBy = "toMember", cascade = CascadeType.ALL)
    private List<ScrapMember> scraptoMemberList;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<ScrapMember> scrapMemberList;
// 여기까지

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Story> storyList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ScrapStory> scrapStoryList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ScrapExhibition> scrapExhibitionList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LikeExhibition> likeExhibitionList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LikeStory> likeStoryList;
}
