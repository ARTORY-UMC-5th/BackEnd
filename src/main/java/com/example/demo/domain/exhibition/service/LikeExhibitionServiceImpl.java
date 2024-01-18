package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.LikeExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.exhibition.repository.LikeExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeExhibitionServiceImpl implements LikeExhibitionService {

    private final LikeExhibitionRepository likeExhibitionRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;



    @Override
    public void likeExhibition(Long memberId, Long exhibitionId) {
        // 해당 맴버와 전시회 정보를 찾음
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionId);

        if (memberOptional.isPresent() && exhibitionOptional.isPresent()) {
            Member member = memberOptional.get();
            Exhibition exhibition = exhibitionOptional.get();

            // 이미 좋아요되었는지 확인
            Optional<LikeExhibition> existingLike = likeExhibitionRepository.findByMemberAndExhibition(member, exhibition);

            if (existingLike.isEmpty()) {
                // 좋아요되지 않았다면 저장
                LikeExhibition likeExhibition = LikeExhibition.builder()
                        .member(member)
                        .exhibition(exhibition)
                        .build();

                likeExhibitionRepository.save(likeExhibition);
            }
        }
    }
}
