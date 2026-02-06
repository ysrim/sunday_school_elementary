package app.psn.std.quest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.quest.vo.StdQuestListVO;
import app.psn.std.quest.vo.StdQuestPendingVO;

@Mapper
public interface StdQuestMapper {

	/**
	 * 퀘스트 목록
	 */
	List<StdQuestListVO> sltQuestList(String mberSn);

	/**
	 * 퀘스트 수행 요청
	 */
	Integer questDo(StdQuestPendingVO questVO);

	/**
	 * 퀘스트 완료 체크
	 */
	Integer questCompleteChk(StdQuestPendingVO questVO);

}