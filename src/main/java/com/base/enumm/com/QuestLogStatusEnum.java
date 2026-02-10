package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestLogStatusEnum {

    PENDING(1, "승인요청"),  //
    APPROVED(2, "승인완료"), //
    REJECTED(3, "반려")      //
    ;

    private final Integer seq;

    private final String description;

    public boolean isSameStatus(String statusName) {
        return this.name().equals(statusName);
    }

}