package com.example.demo.exteranal.oauth.model;

import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.domain.member.constant.Role;
import com.example.demo.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class OAuthAttributes {
    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role){
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(memberType)
                .profile(profile)
                .role(role)
                .build();
    }
}
