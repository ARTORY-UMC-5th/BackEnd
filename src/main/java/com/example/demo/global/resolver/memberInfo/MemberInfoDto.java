package com.example.demo.global.resolver.memberInfo;

import com.example.demo.domain.member.constant.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {
    private Long memberId;
    private Role role;
}
