package app.psn.std.attend.service;

import java.util.List;

import app.psn.std.attend.vo.StdAttendanceVO;

public interface StdAttendanceService {

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