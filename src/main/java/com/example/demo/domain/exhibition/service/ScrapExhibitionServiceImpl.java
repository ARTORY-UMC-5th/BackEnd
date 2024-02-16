package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.exhibition.repository.ScrapExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ScrapExhibitionServiceImpl implements ScrapExhibitionService {

    private final ScrapExhibitionRepository scrapExhibitionRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;


    @Override
    @Transactional
    public void scrapExhibition(@MemberInfo MemberInfoDto memberInfoDto, Long exhibitionId) {
        Long memberId = memberInfoDto.getMemberId();

        // 해당 맴버와 전시회 정보를 찾음
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionId);

        if (memberOptional.isPresent() && exhibitionOptional.isPresent()) {
            Member member = memberOptional.get();
            Exhibition exhibition = exhibitionOptional.get();

            // 이미 스크랩되었는지 확인
            Optional<ScrapExhibition> existingScrap = scrapExhibitionRepository.findByMemberAndExhibition(member, exhibition);


            if (existingScrap.isPresent()) {
                // ScrapExhibition이 존재하면 해당 객체를 가져와 isScrapped를 true로 업데이트
                ScrapExhibition existingScrapExhibition = existingScrap.get();
                existingScrapExhibition.setScrapped(true);
            } else {
                // ScrapExhibition이 존재하지 않으면 새로 ScrapExhibition
                ScrapExhibition scrapExhibition = ScrapExhibition.builder()
                        .member(member)
                        .exhibition(exhibition)
                        .isScrapped(true)
                        .build();

                scrapExhibitionRepository.save(scrapExhibition);

            }

        }
    }

    @Override
    @Transactional
    public void disScrapExhibition(@MemberInfo MemberInfoDto memberInfoDto, Long exhibitionId) {
        Long memberId = memberInfoDto.getMemberId();

        // 해당 맴버와 전시회 정보를 찾음
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionId);

        if (memberOptional.isPresent() && exhibitionOptional.isPresent()) {
            Member member = memberOptional.get();
            Exhibition exhibition = exhibitionOptional.get();

            // 이미 스크랩되었는지 확인
            Optional<ScrapExhibition> existingScrap = scrapExhibitionRepository.findByMemberAndExhibition(member, exhibition);


            if (existingScrap.isPresent()) {
                // ScrapExhibition이 존재하면 해당 객체를 가져와 isScrapped를 true로 업데이트
                ScrapExhibition existingScrapExhibition = existingScrap.get();
                existingScrapExhibition.setScrapped(false);
            } else {
                // ScrapExhibition이 존재하지 않으면 새로 ScrapExhibition
                ScrapExhibition scrapExhibition = ScrapExhibition.builder()
                        .member(member)
                        .exhibition(exhibition)
                        .isScrapped(false)
                        .build();

                scrapExhibitionRepository.save(scrapExhibition);

            }

        }
    }
}