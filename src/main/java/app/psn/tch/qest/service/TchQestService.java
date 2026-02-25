package app.psn.tch.qest.service;

import app.psn.tch.qest.vo.ReqQestProcVO;
import app.psn.tch.qest.vo.TchResQestVO;

import java.util.List;

public interface TchQestService {

	/**
	 * 퀘스트 요청 목록
	 */
	List<TchResQestVO> sltReqQestList(String reqMonth);

	/**
	 * 퀘스트 승인
	 */
	void questProc(ReqQestProcVO reqQestProcVO);

}