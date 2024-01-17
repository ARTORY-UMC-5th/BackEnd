package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.ScrapExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.exhibition.repository.ScrapExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ScrapExhibitionServiceImpl implements ScrapExhibitionService {

    private final ScrapExhibitionRepository scrapExhibitionRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;



    @Override
    public void scrapExhibition(Long memberId, Long exhibitionId) {
        // 해당 맴버와 전시회 정보를 찾음
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionId);

        if (memberOptional.isPresent() && exhibitionOptional.isPresent()) {
            Member member = memberOptional.get();
            Exhibition exhibition = exhibitionOptional.get();

            // 이미 스크랩되었는지 확인
            Optional<ScrapExhibition> existingScrap = scrapExhibitionRepository.findByMemberAndExhibition(member, exhibition);

            if (existingScrap.isEmpty()) {
                // 스크랩되지 않았다면 저장
                ScrapExhibition scrapExhibition = ScrapExhibition.builder()
                        .member(member)
                        .exhibition(exhibition)
                        .build();

                scrapExhibitionRepository.save(scrapExhibition);
            }
        }
    }
}
