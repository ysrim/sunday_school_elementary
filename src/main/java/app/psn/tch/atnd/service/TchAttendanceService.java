package app.psn.tch.atnd.service;

import app.psn.tch.atnd.vo.AtndApprovalVO;
import app.psn.tch.qest.vo.ReqQestProcVO;

import java.util.List;

public interface TchAttendanceService {

	/**
	 * 출석 승인요청 목록
	 */
	List<AtndApprovalVO> sltReqAtndList(String reqDate);

	/**
	 * 출석요청
	 */
	void atndChk(ReqQestProcVO reqQestProcVO);

}