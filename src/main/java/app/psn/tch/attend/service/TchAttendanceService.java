package app.psn.tch.attend.service;

import java.util.List;

import app.psn.tch.attend.vo.ReqAttendanceVO;

public interface TchAttendanceService {

	/**
	 * 출석 요청 목록
	 */
	List<ReqAttendanceVO> sltReqAtndList(String reqDate);

	Integer sltAttendanceContinueCount();

	/**
	 * 출석요청
	 */
	void attendanceDo();

}