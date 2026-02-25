package app.psn.tch.gild.service;

import app.psn.tch.gild.vo.TchGildMemberVO;

import java.util.List;

public interface TchGildService {

	/**
	 * 길드원 목록
	 */
	List<TchGildMemberVO> sltTchGuildMberList();

}