package com.example.demo.global.error;

import lombok.Getter;
import org.aspectj.bridge.IMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {//우리가 사용할 에러코드를 담은 dto
    TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business exception test"),

    // 인증 && 인가
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 refresh token은 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 만료됐습니다. 다시 로그인 해주세요."),
    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다."),
    FORBIDDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008", "관리자 Role이 아닙니다."),
    INVALID_PASSWORD(HttpStatus.FORBIDDEN, "A-009", "아이디에 맞는 비밀번호가 아닙니다."),

    // 회원
    INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "M-001", "잘못된 회원 타입 입니다."),
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입된 회원 입니다."),
    MEMBER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "M-003", "해당 회원은 존재하지 않습니다."),
    ALREADY_REGISTERED_EMAIL(HttpStatus.BAD_REQUEST, "M-004", "이미 다른 소셜로그인으로 가입된 이메일 입니다."),
    MISSING_REQUIRED_VALUE(HttpStatus.BAD_REQUEST, "M-005", "필요 값(body)이 누락되었습니다."),
    NOT_YOUR_MEMBER(HttpStatus.BAD_REQUEST, "M-006", "본인의 회원이 아닙니다."),


    //전시회
    EXHIBITION_NOT_EXISTS(HttpStatus.BAD_REQUEST, "E-003", "해당 전시회는 존재하지 않습니다."),


    // 스토리
    STORY_NOT_EXISTS(HttpStatus.BAD_REQUEST, "S-003", "해당 스토리는 존재하지 않습니다."),
    NOT_YOUR_STORY(HttpStatus.BAD_REQUEST, "S-004", "본인의 스토리가 아닙니다."),
    CANNOT_CREATE_STORY(HttpStatus.BAD_REQUEST, "S-005", "필수 body 값이 누락되어 스토리가 생성되지 않습니다."),
    CANNOT_UPDATE_STORY(HttpStatus.BAD_REQUEST, "S-006", "필수 body 값이 누락되어 스토리가 수정되지 않습니다."),
    CANNOT_DELETE_STORY(HttpStatus.BAD_REQUEST, "S-007", "본인의 스토리가 아닙니다. 스토리를 삭제할 수 없습니다."),
    STORY_PRIVATE(HttpStatus.BAD_REQUEST, "S-009", "해당 스토리는 비공개 처리 되어있습니다."),


    // 댓글
    COMMENT_NOT_EXISTS(HttpStatus.BAD_REQUEST, "C-001", "해당 댓글은 존재하지 않습니다."),
    NOT_YOUR_COMMENT(HttpStatus.BAD_REQUEST, "C-002", "본인의 댓글이 아닙니다."),
    CANNOT_CREATE_COMMENT(HttpStatus.BAD_REQUEST, "C-003", "body값이 누락되어 댓글이 생성되지 않습니다."),
    CANNOT_UPDATE_COMMENT(HttpStatus.BAD_REQUEST, "C-004", "body값이 누락되어 댓글이 수정되지 않습니다."),
    CANNOT_DELETE_COMMENT(HttpStatus.BAD_REQUEST, "C-004", "본인의 댓글이 아닙니다. 댓글을 삭제할 수 없습니다."),
    DELETED_COMMENT(HttpStatus.BAD_REQUEST, "C-003", "이미 삭제된 댓글입니다."),


    // 좋아요
    LIKE_EXISTS(HttpStatus.BAD_REQUEST, "S-005", "이미 좋아요 한 상태입니다."),
    UNLIKE_EXISTS(HttpStatus.BAD_REQUEST, "S-006", "이미 좋아요 하지 않은 상태입니다."),

    // 스크랩
    SCRAP_EXISTS(HttpStatus.BAD_REQUEST, "S-007", "이미 스크랩을 한 상태입니다."),
    UNSCRAP_EXISTS(HttpStatus.BAD_REQUEST, "S-008", "이미 스크랩 하지 않은 상태입니다."),
    NOT_RECOMMEND(HttpStatus.BAD_REQUEST, "S-009", "스크랩을 하지 않아 추천이 뜨지 않습니다.");


    ErrorCode(HttpStatus httpStatus, String errorCode, String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
