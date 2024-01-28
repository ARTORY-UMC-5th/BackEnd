package com.example.demo.domain.member.service;


import com.example.demo.global.resolver.memberInfo.MemberInfoDto;

public interface ScrapMemberService {

    void scrapMember(MemberInfoDto memberInfoDto, Long toMemberId);

    void unscrapMember(MemberInfoDto memberInfoDto, Long toMemberId);
}
