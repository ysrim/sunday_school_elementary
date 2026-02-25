package app.psn.tch.qest.mapper;

import app.psn.tch.qest.vo.ReqQestProcVO;
import app.psn.tch.qest.vo.TchResQestVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TchQestMapper {

	/**
	 * 월별 퀘스트 요청 목록
	 */
	List<TchResQestVO> sltReqQestList(@Param("guildSn") Integer guildSn, @Param("reqMonth") String reqMonth);

	/**
	 * 퀘스트 승인
	 */
	void questProc(ReqQestProcVO reqQestProcVO);

}