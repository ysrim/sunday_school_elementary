package app.psn.tch.attend.service;

import app.psn.tch.attend.vo.StdAttendanceVO;

import java.util.List;

public interface TchAttendanceService {

    /**
     * 당월 주일 출석 목록
     */
    List<StdAttendanceVO> sltAttendanceList();

    /**
     * 연속출석일 수
     */
    Integer sltAttendanceContinueCount();

    /**
     * 출석요청
     */
    void attendanceDo();

}