package com.example.demo.domain.member.service;

import com.example.demo.domain.member.entity.ScrapMember;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.repository.ScrapMemberRepository;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.StoryException;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ScrapMemberServiceImpl implements ScrapMemberService{

    private final MemberRepository memberRepository;
    private final ScrapMemberRepository scrapMemberRepository;


    @Transactional
    public void scrapMember(MemberInfoDto memberInfoDto, Long toMemberId) {

        Long fromMemberId = memberInfoDto.getMemberId();

        //스크랩 정보가 없을 시 ->
        ScrapMember temp = scrapMemberRepository.findByfromMemberIdAndtoMemberId(fromMemberId, toMemberId);

        if (temp == null) {
            ScrapMember scrapMember = ScrapMember.builder()
                    .fromMember(memberRepository.getById(fromMemberId))
                    .toMember(memberRepository.getById(toMemberId))
                    .isScrapped(true)
                    .build();

            scrapMemberRepository.save(scrapMember);
        } else if (temp.getIsScrapped() == null || temp.getIsScrapped() == false) {
            scrapMemberRepository.setIsScrappedTrue(temp);
        } else {
            throw new StoryException(ErrorCode.SCRAP_EXISTS);
        }
    }

    @Transactional
    public void unscrapMember(MemberInfoDto memberInfoDto, Long toMemberId) {

        Long fromMemberId = memberInfoDto.getMemberId();

        ScrapMember scrapMember = scrapMemberRepository.findByfromMemberIdAndtoMemberId(fromMemberId, toMemberId);

        if (scrapMember == null || scrapMember.getIsScrapped() == null || !scrapMember.getIsScrapped()) {
            throw new StoryException(ErrorCode.UNSCRAP_EXISTS);
        } else {
            scrapMemberRepository.setIsScrappedFalse(scrapMember);
        }
    }
}