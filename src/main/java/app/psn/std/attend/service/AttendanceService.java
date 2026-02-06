package app.psn.std.attend.service;

import java.util.List;

import app.psn.std.attend.vo.AttendanceVO;

public interface AttendanceService {

    /**
     * 당월 주일 출석 목록
     */
    List<AttendanceVO> sltAttendanceList();

    /**
     * 연속출석일 수
     */
    Integer sltAttendanceContinueCount();

    /**
     * 출석요청
     */
    void attendanceDo();

}