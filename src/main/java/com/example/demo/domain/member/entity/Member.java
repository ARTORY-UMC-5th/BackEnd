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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
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
    @Setter
    private String email;

    @Column(length = 200)
    @Setter
    private String password;

    @Column(nullable = false, length = 20)
    @Setter
    private String memberName;

    @Column(length = 200)
    private String profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    public Member(MemberType memberType, String email, String password, String memberName,
                  String profile, Role role) {
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.profile = profile;
        this.role = role;
    }

    public Member(MemberInfoSaveDto memberInfosaveDto) {
        super();
    }

    public static MemberBuilder builder() {
        return new MemberBuilder();
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
    @Column(nullable = true, length = 200)
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

    //form login에서 사용
    @Setter
    @Column(nullable = false)
    private String confirmPassword;
    @Setter
    @Column(unique = true, nullable = false)
    private String phoneNum;
    @Setter
    @Column(nullable = false)
    private String birth;

    private boolean termsAgreed;
    private boolean privacyPolicyAgreed;
    private boolean marketingAgreed;

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

    public MemberBuilder toBuilder() {
        return new MemberBuilder().memberId(this.memberId).memberType(this.memberType).email(this.email).password(this.password).memberName(this.memberName).profile(this.profile).role(this.role).refreshToken(this.refreshToken).tokenExpirationTime(this.tokenExpirationTime).nickname(this.nickname).image(this.image).gender(this.gender).age(this.age).genre1(this.genre1).genre2(this.genre2).genre3(this.genre3).introduction(this.introduction).myKeyword(this.myKeyword).memo(this.memo).confirmPassword(this.confirmPassword).phoneNum(this.phoneNum).birth(this.birth).termsAgreed(this.termsAgreed).privacyPolicyAgreed(this.privacyPolicyAgreed).marketingAgreed(this.marketingAgreed).scrapfromMemberList(this.scrapfromMemberList).scraptoMemberList(this.scraptoMemberList).storyList(this.storyList).scrapStoryList(this.scrapStoryList).commentList(this.commentList).scrapExhibitionList(this.scrapExhibitionList).likeExhibitionList(this.likeExhibitionList).likeStoryList(this.likeStoryList);
    }

    public static class MemberBuilder {
        private Long memberId;
        private MemberType memberType;
        private String email;
        private String password;
        private String memberName;
        private String profile;
        private Role role;
        private String refreshToken;
        private LocalDateTime tokenExpirationTime;
        private String nickname;
        private String image;
        private Gender gender;
        private Integer age;
        private Genre genre1;
        private Genre genre2;
        private Genre genre3;
        private String introduction;
        private String myKeyword;
        private String memo;
        private String confirmPassword;
        private String phoneNum;
        private String birth;
        private boolean termsAgreed;
        private boolean privacyPolicyAgreed;
        private boolean marketingAgreed;
        private List<ScrapMember> scrapfromMemberList;
        private List<ScrapMember> scraptoMemberList;
        private List<Story> storyList;
        private List<ScrapStory> scrapStoryList;
        private List<Comment> commentList;
        private List<ScrapExhibition> scrapExhibitionList;
        private List<LikeExhibition> likeExhibitionList;
        private List<LikeStory> likeStoryList;

        MemberBuilder() {
        }

        public MemberBuilder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public MemberBuilder memberType(MemberType memberType) {
            this.memberType = memberType;
            return this;
        }

        public MemberBuilder email(String email) {
            this.email = email;
            return this;
        }

        public MemberBuilder password(String password) {
            this.password = password;
            return this;
        }

        public MemberBuilder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }

        public MemberBuilder profile(String profile) {
            this.profile = profile;
            return this;
        }

        public MemberBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public MemberBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public MemberBuilder tokenExpirationTime(LocalDateTime tokenExpirationTime) {
            this.tokenExpirationTime = tokenExpirationTime;
            return this;
        }

        public MemberBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public MemberBuilder image(String image) {
            this.image = image;
            return this;
        }

        public MemberBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public MemberBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public MemberBuilder genre1(Genre genre1) {
            this.genre1 = genre1;
            return this;
        }

        public MemberBuilder genre2(Genre genre2) {
            this.genre2 = genre2;
            return this;
        }

        public MemberBuilder genre3(Genre genre3) {
            this.genre3 = genre3;
            return this;
        }

        public MemberBuilder introduction(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public MemberBuilder myKeyword(String myKeyword) {
            this.myKeyword = myKeyword;
            return this;
        }

        public MemberBuilder memo(String memo) {
            this.memo = memo;
            return this;
        }

        public MemberBuilder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public MemberBuilder phoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        public MemberBuilder birth(String birth) {
            this.birth = birth;
            return this;
        }

        public MemberBuilder termsAgreed(boolean termsAgreed) {
            this.termsAgreed = termsAgreed;
            return this;
        }

        public MemberBuilder privacyPolicyAgreed(boolean privacyPolicyAgreed) {
            this.privacyPolicyAgreed = privacyPolicyAgreed;
            return this;
        }

        public MemberBuilder marketingAgreed(boolean marketingAgreed) {
            this.marketingAgreed = marketingAgreed;
            return this;
        }

        public MemberBuilder scrapfromMemberList(List<ScrapMember> scrapfromMemberList) {
            this.scrapfromMemberList = scrapfromMemberList;
            return this;
        }

        public MemberBuilder scraptoMemberList(List<ScrapMember> scraptoMemberList) {
            this.scraptoMemberList = scraptoMemberList;
            return this;
        }

        public MemberBuilder storyList(List<Story> storyList) {
            this.storyList = storyList;
            return this;
        }

        public MemberBuilder scrapStoryList(List<ScrapStory> scrapStoryList) {
            this.scrapStoryList = scrapStoryList;
            return this;
        }

        public MemberBuilder commentList(List<Comment> commentList) {
            this.commentList = commentList;
            return this;
        }

        public MemberBuilder scrapExhibitionList(List<ScrapExhibition> scrapExhibitionList) {
            this.scrapExhibitionList = scrapExhibitionList;
            return this;
        }

        public MemberBuilder likeExhibitionList(List<LikeExhibition> likeExhibitionList) {
            this.likeExhibitionList = likeExhibitionList;
            return this;
        }

        public MemberBuilder likeStoryList(List<LikeStory> likeStoryList) {
            this.likeStoryList = likeStoryList;
            return this;
        }

        public Member build() {
            return new Member(this.memberId, this.memberType, this.email, this.password, this.memberName, this.profile, this.role, this.refreshToken, this.tokenExpirationTime, this.nickname, this.image, this.gender, this.age, this.genre1, this.genre2, this.genre3, this.introduction, this.myKeyword, this.memo, this.confirmPassword, this.phoneNum, this.birth, this.termsAgreed, this.privacyPolicyAgreed, this.marketingAgreed, this.scrapfromMemberList, this.scraptoMemberList, this.storyList, this.scrapStoryList, this.commentList, this.scrapExhibitionList, this.likeExhibitionList, this.likeStoryList);
        }

        public String toString() {
            return "Member.MemberBuilder(memberId=" + this.memberId + ", memberType=" + this.memberType + ", email=" + this.email + ", password=" + this.password + ", memberName=" + this.memberName + ", profile=" + this.profile + ", role=" + this.role + ", refreshToken=" + this.refreshToken + ", tokenExpirationTime=" + this.tokenExpirationTime + ", nickname=" + this.nickname + ", image=" + this.image + ", gender=" + this.gender + ", age=" + this.age + ", genre1=" + this.genre1 + ", genre2=" + this.genre2 + ", genre3=" + this.genre3 + ", introduction=" + this.introduction + ", myKeyword=" + this.myKeyword + ", memo=" + this.memo + ", confirmPassword=" + this.confirmPassword + ", phoneNum=" + this.phoneNum + ", birth=" + this.birth + ", termsAgreed=" + this.termsAgreed + ", privacyPolicyAgreed=" + this.privacyPolicyAgreed + ", marketingAgreed=" + this.marketingAgreed + ", scrapfromMemberList=" + this.scrapfromMemberList + ", scraptoMemberList=" + this.scraptoMemberList + ", storyList=" + this.storyList + ", scrapStoryList=" + this.scrapStoryList + ", commentList=" + this.commentList + ", scrapExhibitionList=" + this.scrapExhibitionList + ", likeExhibitionList=" + this.likeExhibitionList + ", likeStoryList=" + this.likeStoryList + ")";
        }
    }
}