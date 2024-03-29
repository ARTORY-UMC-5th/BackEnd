package com.example.demo.domain.member.controller;


import com.example.demo.domain.member.dto.MemberInfoResponseDto;

import com.example.demo.domain.member.dto.MemberInfoSaveDto;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.member.service.MemberInfoService;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.BusinessException;
import com.example.demo.global.jwt.service.TokenManager;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "사용자 정보 조회, 수정", description = "사용자 정보, 로그아웃 API")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberInfoService memberInfoService;
    private final MemberRepository memberRepository;
    @Operation(summary = "사용자 전체 정보", description = "수정하기 전에 전체 정보를 불러와서 채워주세요. 기존 데이터에 덮어 쓰기 위함")
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(@MemberInfo MemberInfoDto memberInfoDto) {

        Long memberId = memberInfoDto.getMemberId();
        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(memberId);

        return ResponseEntity.ok(memberInfoResponseDto);
    }
    @Operation(summary = "사용자 정보 전체 수정", description = "일부 수정 불가능한 정보는 빠져있음")
    @PostMapping("/save/all")
    public ResponseEntity<String> saveMemberInfo(@MemberInfo MemberInfoDto memberInfoDto, @RequestBody MemberInfoSaveDto.MemberInfo memberInfoSaveDto) {

        Member member = memberInfoService.saveMemberInfo( memberInfoSaveDto, memberInfoDto.getMemberId());
        memberRepository.save(member);

        return ResponseEntity.ok("saved");
    }
    @Operation(summary = "사용자 닉네임과 아토리 프로필 사진 저장", description = "사진은 링크형태로 받음, 이후 개발 수정 가능")
    @PostMapping("/save/nickname")
    public ResponseEntity<String> saveMemberNickname(
            @RequestParam String nickname,
            @RequestParam String image,
            @MemberInfo MemberInfoDto memberInfoDto)
    {
        MemberInfoSaveDto.MemberNickname memberNickname = MemberInfoSaveDto.MemberNickname.builder()
                .nickname(nickname)
                .image(image)
                .build();
        Member member = memberInfoService.saveMemberNickname(memberNickname, memberInfoDto.getMemberId());
        memberRepository.save(member);

        return ResponseEntity.ok("nickname-saved");
    }
    @Operation(summary = "사용자 나이, 성별 저장", description = "나이는 int")
    @PostMapping("/save/age-gender")
    public ResponseEntity<String> saveMemberAgeAndGender(@MemberInfo MemberInfoDto memberInfoDto, @RequestBody MemberInfoSaveDto.MemberAgeAndGender memberInfoSaveDto) {

        Member member = memberInfoService.saveMemberAgeAndGender(memberInfoSaveDto, memberInfoDto.getMemberId());
        memberRepository.save(member);

        return ResponseEntity.ok("age-gender-saved");
    }
    @Operation(summary = "사용자 장르 수정", description = "장르 MEDIA,CRAFT,DESIGN,PICTURE,SPECIAL_EXHIBITION,SCULPTURE,PLANEXHIBITION,\n" +
            "    INSTALLATION_ART,PAINTING,ARTIST_EXHIBITION")
    @PostMapping("/save/genre")
    public ResponseEntity<String> saveMemberGenre(@MemberInfo MemberInfoDto memberInfoDto, @RequestBody MemberInfoSaveDto.MemberGenre memberInfoSaveDto) {

        Member member = memberInfoService.saveMemberGenre(memberInfoSaveDto, memberInfoDto.getMemberId());
        memberRepository.save(member);

        return ResponseEntity.ok("genre-saved");
    }

    @Operation(summary = "사용자 삭제 ㄷㄷ")
    @DeleteMapping("/delete-member")
    public ResponseEntity<String> deleteMember(@MemberInfo MemberInfoDto memberInfoDto, @RequestParam Long memberId) {
        if (!Objects.equals(memberInfoDto.getMemberId(), memberId)) {
            throw new BusinessException(ErrorCode.NOT_YOUR_MEMBER);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));
        memberRepository.delete(member);

        return ResponseEntity.ok("member-deleted");
    }
    @Operation(summary = "사용자 비밀번호 저장")
    @PostMapping("/save/pwchange")
    public ResponseEntity<String> saveMemberPW(@MemberInfo MemberInfoDto memberInfoDto, @RequestBody MemberInfoSaveDto.passwordDto reqPassword) {

        Member member = memberInfoService.saveMemberPw(reqPassword.getPassword(), memberInfoDto.getMemberId());
        memberRepository.save(member);

        return ResponseEntity.ok("pass-word-changed");
    }
}
