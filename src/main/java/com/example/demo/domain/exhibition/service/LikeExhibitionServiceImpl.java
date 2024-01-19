package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.entity.LikeExhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionRepository;
import com.example.demo.domain.exhibition.repository.LikeExhibitionRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeExhibitionServiceImpl implements LikeExhibitionService {

    private final LikeExhibitionRepository likeExhibitionRepository;
    private final MemberRepository memberRepository;
    private final ExhibitionRepository exhibitionRepository;



    @Override
    @Transactional
    public void likeExhibition(Long memberId, Long exhibitionId) {
        // 해당 맴버와 전시회 정보를 찾음
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionId);

        if (memberOptional.isPresent() && exhibitionOptional.isPresent()) {
            Member member = memberOptional.get();
            Exhibition exhibition = exhibitionOptional.get();

            // LikeExhibition이 이미 존재하는지 확인
            Optional<LikeExhibition> existingLikeExhibitionOptional = likeExhibitionRepository.findByMemberAndExhibition(member, exhibition);

            if (existingLikeExhibitionOptional.isPresent()) {
                // LikeExhibition이 존재하면 해당 객체를 가져와 isLiked를 true로 업데이트
                LikeExhibition existingLikeExhibition = existingLikeExhibitionOptional.get();
                existingLikeExhibition.setLiked(true);
            } else {
                // LikeExhibition이 존재하지 않으면 새로 생성
                LikeExhibition likeExhibition = LikeExhibition.builder()
                        .member(member)
                        .exhibition(exhibition)
                        .isLiked(true)
                        .build();

                likeExhibitionRepository.save(likeExhibition);

                // 해당 전시회의 전체 좋아요 수를 1 증가시킴
                exhibition.setExhibitionLikeCount(exhibition.getExhibitionLikeCount() + 1);
            }
        }
    }

    @Override
    @Transactional
    public void disLikeExhibition(Long memberId, Long exhibitionId) {
        // 해당 맴버와 전시회 정보를 찾음
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Exhibition> exhibitionOptional = exhibitionRepository.findById(exhibitionId);

        if (memberOptional.isPresent() && exhibitionOptional.isPresent()) {
            Member member = memberOptional.get();
            Exhibition exhibition = exhibitionOptional.get();

            // LikeExhibition이 이미 존재하는지 확인
            Optional<LikeExhibition> existingLikeExhibitionOptional = likeExhibitionRepository.findByMemberAndExhibition(member, exhibition);

            if (existingLikeExhibitionOptional.isPresent()) {
                // LikeExhibition이 존재하면 해당 객체를 가져와 isLiked를 false로 업데이트
                LikeExhibition existingLikeExhibition = existingLikeExhibitionOptional.get();
                existingLikeExhibition.setLiked(false);
            } else {
                // LikeExhibition이 존재하지 않으면 새로 생성
                LikeExhibition likeExhibition = LikeExhibition.builder()
                        .member(member)
                        .exhibition(exhibition)
                        .isLiked(false)
                        .build();

                likeExhibitionRepository.save(likeExhibition);

                // 해당 전시회의 전체 좋아요 수를 1 감소시킴
                exhibition.setExhibitionLikeCount(exhibition.getExhibitionLikeCount() - 1);
            }
        }
    }
}
