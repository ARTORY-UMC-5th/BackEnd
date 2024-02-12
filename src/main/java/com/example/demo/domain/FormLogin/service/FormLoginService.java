package com.example.demo.domain.FormLogin.service;

import com.example.demo.domain.FormLogin.dto.FormLoginRequestDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.BusinessException;
import com.example.demo.global.jwt.dto.JwtTokenDto;
import com.example.demo.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FormLoginService {

    private final MemberService memberService;
    private final TokenManager tokenManager;


    //로그인
    public JwtTokenDto signIn(String email, String password) {
        // 입력받은 이메일로 등록된 회원이 있는지 확인
        Optional<Member> optionalMember = memberService.findMemberByEmail(email);
        if (optionalMember.isEmpty()) {
            // 등록된 회원이 없는 경우
            throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);
        }

        // 등록된 회원이 있는 경우 비밀번호 일치 여부 확인
        Member member = optionalMember.get();
        if (!member.getPassword().equals(password)) {
            // 비밀번호가 일치하지 않는 경우
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        // 비밀번호가 일치하면 해당 회원의 정보를 기반으로 JWT 토큰 생성하여 반환
        JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(member.getMemberId(), member.getRole(),true);
        jwtTokenDto.setRefreshToken(member.getRefreshToken());

        return jwtTokenDto;
    }

    //회원가입
    public JwtTokenDto signUp(FormLoginRequestDto.SignUpRequestDto signupRequest) {

        // 회원가입 처리
        Member newMember = new Member();
        newMember.setEmail(signupRequest.getEmail());
        newMember.setPassword(signupRequest.getPassword());
        newMember.setMemberName(signupRequest.getMemberName());
        newMember.setMemberType(signupRequest.getMemberType());
        newMember.setRole(signupRequest.getRole());
        // 다른 회원가입 관련 필드 설정

        // 회원 등록
        Member registeredMember = memberService.registerMember(newMember);

        // 회원의 정보를 기반으로 JWT 토큰 생성하여 반환
        JwtTokenDto jwtTokenDto = tokenManager.createJwtTokenDto(registeredMember.getMemberId(), registeredMember.getRole(), false);
        registeredMember.updateRefreshToken(jwtTokenDto);

        return jwtTokenDto;
    }
}

