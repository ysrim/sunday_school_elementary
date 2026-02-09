package app.psn.tch.attend.service;

import app.psn.tch.attend.vo.StdAttendanceVO;

import java.util.List;
import java.util.Map;

public interface TchAttendanceService {

    /**
     * 월 리스트
     */
    List<String> sltMonthList();

    /**
     * 월에 속한 리스트
     */
    List<String> sltWeekList(String month);


    Integer sltAttendanceContinueCount();

    /**
     * 출석요청
     */
    void attendanceDo();

}