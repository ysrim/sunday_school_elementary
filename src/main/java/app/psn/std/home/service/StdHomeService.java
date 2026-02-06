package app.psn.std.home.service;

import java.util.List;

import app.psn.std.home.vo.StdHomeGuildInfoVO;
import app.psn.std.home.vo.StdHomeGuildListVO;

public interface StdHomeService {

	/**
	 * 길드원 목록
	 */
	List<StdHomeGuildListVO> sltGuildMberList();

	/**
	 * 현재 로그인한 길드원 목록
	 */
	List<StdHomeGuildListVO> sltGuildMberAccessList();

	/**
	 * 길드정보
	 */
	StdHomeGuildInfoVO sltGuildInfo();

	/**
	 * 오늘의 말씀 클릭
	 */
	boolean wordsAmenDo();

}