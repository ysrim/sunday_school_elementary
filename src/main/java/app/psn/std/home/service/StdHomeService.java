package app.psn.std.home.service;

import java.util.List;

import app.psn.std.home.vo.StdHomeGildInfoVO;
import app.psn.std.home.vo.StdHomeGildVO;

public interface StdHomeService {

	/**
	 * 길드원 목록
	 */
	List<StdHomeGildVO> sltGildMberList();

	/**
	 * 현재 로그인한 길드원 목록
	 */
	List<StdHomeGildVO> sltGildMberAccessList();

	/**
	 * 길드정보
	 */
	StdHomeGildInfoVO sltGildInfo();

	/**
	 * 오늘의 말씀 클릭
	 */
	boolean wordsAmenDo();

}