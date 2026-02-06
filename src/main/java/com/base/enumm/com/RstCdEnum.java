package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RstCdEnum {

    SUCC("001", "Success"),            // 성공
    VALID("002", "Validation Failed"), // valid error
    FAIL("999", "An error occurred")   // 에러
    ;

    private final String code;
    private final String defaultMessage;

}