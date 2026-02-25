package app.psn.mng.qest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.mng.qest.vo.MngQestVO;
import app.psn.mng.qest.vo.MngReqQuestProcVO;

@Mapper
public interface MngQestMapper {

	/**
	 * 퀘스트 수행 목록
	 */
	List<MngQestVO> getQestLogList();

	/**
	 * 퀘스트 승인
	 */
	void questProc(MngReqQuestProcVO mngReqQuestProcVO);

}