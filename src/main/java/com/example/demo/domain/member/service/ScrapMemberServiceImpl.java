package com.example.demo.domain.member.service;

import com.example.demo.domain.member.repository.ScrapMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapMemberServiceImpl implements ScrapMemberService{

    private final ScrapMemberRepository scrapMemberRepository;


    @Transactional
    public Boolean findExistByIds(Long from, Long to) {
        return Boolean.valueOf(scrapMemberRepository.existsByfromMemberIdAndtoMemberId(from, to));
    }
}
