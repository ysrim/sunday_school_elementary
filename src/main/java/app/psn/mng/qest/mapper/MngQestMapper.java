package app.psn.mng.qest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.mng.qest.vo.StdQestListVO;
import app.psn.mng.qest.vo.StdQestPendingVO;

@Mapper
public interface MngQestMapper {

	/**
	 * 퀘스트 목록
	 */
	List<StdQestListVO> sltQestList(String mberSn);

	/**
	 * 퀘스트 수행 요청
	 */
	Integer qestDo(StdQestPendingVO questVO);

	/**
	 * 퀘스트 완료 체크
	 */
	Integer qestCompleteChk(StdQestPendingVO questVO);

}