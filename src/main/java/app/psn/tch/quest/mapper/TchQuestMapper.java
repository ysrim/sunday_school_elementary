package app.psn.tch.quest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.tch.quest.vo.ReqQuestProcVO;
import app.psn.tch.quest.vo.TchReqQestVO;

@Mapper
public interface TchQuestMapper {

	/**
	 * 월별 퀘스트 요청 목록
	 */
	List<TchReqQestVO> sltReqQestList(@Param("guildSn") Integer guildSn, @Param("reqMonth") String reqMonth);

	/**
	 * 퀘스트 승인
	 */
	void questProc(ReqQuestProcVO reqQuestProcVO);

}