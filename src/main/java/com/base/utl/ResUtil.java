package com.base.utl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.base.enumm.com.RstCdEnum;
import com.base.vo.BodyResVO;

public class ResUtil {

    // 인스턴스화 방지
    private ResUtil() {
    }

    /**
     * 성공 응답 (데이터 포함)
     */
    public static <T> ResponseEntity<BodyResVO<T>> resSucc(String msg, T data) {

        return createResponse(RstCdEnum.SUCC, msg, data);

    }

    /**
     * 성공 응답 (데이터 포함)
     */
    public static <T> ResponseEntity<BodyResVO<T>> resSucc(String msg) {

        return createResponse(RstCdEnum.SUCC, msg, null);

    }

    /**
     * 성공 응답 (데이터 없음 - 필요 시 사용)
     */
    public static <T> ResponseEntity<BodyResVO<T>> resSucc() {

        return createResponse(RstCdEnum.SUCC, null, null);

    }

    /**
     * 실패 응답
     * <T>를 붙여주어 호출하는 쪽에서 타입을 명시해도 경고가 뜨지 않게 함
     */
    public static <T> ResponseEntity<BodyResVO<T>> resFail(String msg) {

        return createResponse(RstCdEnum.FAIL, msg, null);

    }

    /**
     * 유효성 검증 실패 응답
     */
    public static <T> ResponseEntity<BodyResVO<T>> resValid(String msg) {

        return createResponse(RstCdEnum.VALID, msg, null);

    }

    /**
     * 내부 공통 응답 생성 메서드 (Builder 패턴 통일)
     */
    private static <T> ResponseEntity<BodyResVO<T>> createResponse(RstCdEnum rstEnum, String msg, T data) {

        BodyResVO<T> body = BodyResVO.<T>builder()
                .rtnCd(rstEnum.getCode())
                .rtnMsg(msg != null ? msg : rstEnum.getDefaultMessage())
                .rtnData(data)
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);

    }

}