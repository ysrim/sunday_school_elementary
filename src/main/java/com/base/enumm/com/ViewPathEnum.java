package com.base.enumm.com;

public enum ViewPathEnum {

    DEF("/app"),              // 기본 루트
    STD("/app/psn/std/page"), // 학생
    TCH("/app/psn/tch/page"), // 선생
    ADM("/app/psn/adm/page")  // 관리자
    ;

    private final String path;

    ViewPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String to(String fileName) {
        return this.path + fileName;
    }

}