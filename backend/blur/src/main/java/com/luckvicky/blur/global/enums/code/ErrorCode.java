package com.luckvicky.blur.global.enums.code;

import static com.luckvicky.blur.global.constant.ErrorMessage.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * [400 Bad Request]
     * - 응답 상태 코드는 서버가 클라이언트 오류를 감지해 요청 불가
     */
    INVALID_BOARD_TYPE(HttpStatus.BAD_REQUEST, INVALID_BOARD_TYPE_MESSAGE),
    INVALID_LEAGUE_TYPE(HttpStatus.BAD_REQUEST, INVALID_LEAGUE_TYPE_MESSAGE),
    INVALID_SORTING_CRITERIA(HttpStatus.BAD_REQUEST, INVALID_SORTING_CRITERIA_MESSAGE),
    INVALID_SEARCH_CONDITION(HttpStatus.BAD_REQUEST, INVALID_SEARCH_CONDITION_MESSAGE),
    INVALID_EMAIL_CODE(HttpStatus.BAD_REQUEST, INVALID_EMAIL_CODE_MESSAGE),
    INVALID_EMAIL_VERIFIED(HttpStatus.BAD_REQUEST, INVALID_EMAIL_VERIFIED_MESSAGE),
    INVALID_BRAND(HttpStatus.BAD_REQUEST, INVALID_BRAND_MESSAGE),
    INVALID_MODEL(HttpStatus.BAD_REQUEST, INVALID_MODEL_MESSAGE),

    FAIL_TO_VALIDATE(HttpStatus.BAD_REQUEST, FAIL_TO_VALIDATE_MESSAGE),

    // 사용자 관련 요청 예외
    MISSMATCH_PASSWORD(HttpStatus.BAD_REQUEST, MISSMATCH_PASSWORD_MESSAGE),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, DUPLICATE_EMAIL_MESSAGE),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, DUPLICATE_NICKNAME_MESSAGE),
    EXPIRED_EMAIL_CODE(HttpStatus.BAD_REQUEST, EXPIRED_EMAIL_CODE_MESSAGE),
    ALREADY_VOTED(HttpStatus.BAD_REQUEST, ALREADY_VOTED_MESSAGE),
    KEYWORD_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, KEYWORD_LIMIT_EXCEEDED_MESSAGE),

    /**
     * [401 UnAuthorized]
     * - 요청된 리소스에 대한 유효한 인증 자격 증명이 없음
     */
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_ACCESS_MESSAGE),

    /**
     * [403 Forbidden]
     * - 요청한 자원에 대해 권한 없음
     */
    NOT_ALLOCATED_LEAGUE(HttpStatus.FORBIDDEN, NOT_ALLOCATED_LEAGUE_MESSAGE),
    UNAUTHORIZED_BOARD_DELETE(HttpStatus.FORBIDDEN, UNAUTHORIZED_BOARD_DELETE_MESSAGE),

    /**
     * [404 Not Found]
     * - 존재하지 않는 자원
     */
    NOT_EXIST_COMMENT(HttpStatus.NOT_FOUND, NOT_EXIST_COMMENT_MESSAGE),
    NOT_EXIST_BOARD(HttpStatus.NOT_FOUND, NOT_EXIST_BOARD_MESSAGE),
    NOT_EXIST_LEAGUE(HttpStatus.NOT_FOUND, NOT_EXIST_LEAGUE_MESSAGE),
    NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND, NOT_EXIST_MEMBER_MESSAGE),
    NOT_EXIST_DASHCAM(HttpStatus.NOT_FOUND, NOT_EXIST_DASHCAM_MESSAGE),
    NOT_EXIST_CHANNEL(HttpStatus.NOT_FOUND, NOT_EXIST_CHANNEL_MESSAGE),
    NOT_EXIST_LIKE(HttpStatus.NOT_FOUND, NOT_EXIST_LIKE_MESSAGE),
    NOT_EXIST_FOLLOW(HttpStatus.NOT_FOUND, NOT_EXIST_FOLLOW_MESSAGE),
    NOT_EXIST_ALARM(HttpStatus.NOT_FOUND, NOT_EXIST_ALARM_MESSAGE),
    NOT_EXIST_OPTION(HttpStatus.NOT_FOUND, NOT_EXIST_OPTION_MESSAGE),

    /**
     * [500 INTERNAL_SERVER_ERROR]
     * - 서버 오류
     */
    FAIL_TO_CREATE_COMMENT(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_CREATE_COMMENT_MESSAGE),
    FAIL_TO_CREATE_LEAGUE(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_CREATE_LEAGUE_MESSAGE),
    FAIL_TO_CREATE_BOARD(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_CREATE_BOARD_MESSAGE),
    FAIL_TO_CREATE_LIKE(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_CREATE_LIKE_MESSAGE),
    FAIL_TO_CREATE_THUMBNAIL(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_CREATE_THUMBNAIL_MESSAGE),

    FAIL_TO_DELETE_LIKE(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_DELETE_LIKE_MESSAGE),
    FAIL_TO_DELETE_COMMENT(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_DELETE_COMMENT_MESSAGE),
    FAIL_TO_CREATE_FOLLOW(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_CREATE_FOLLOW_MESSAGE),
    FAIL_TO_DELETE_FOLLOW(HttpStatus.INTERNAL_SERVER_ERROR, FAIL_TO_DELETE_FOLLOW_MESSAGE);

    private final HttpStatus code;
    private final String message;

}
