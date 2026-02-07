package app.psn.tch.home.service;

import app.psn.tch.home.vo.StdHomeGuildInfoVO;
import app.psn.tch.home.vo.StdHomeGuildListVO;

import java.util.List;

public interface TchHomeService {

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