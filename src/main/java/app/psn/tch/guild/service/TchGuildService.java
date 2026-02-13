package app.psn.tch.guild.service;

import app.psn.tch.guild.vo.TchGuildMemberVO;

import java.util.List;

public interface TchGuildService {

	/**
	 * 길드원 목록
	 */
	List<TchGuildMemberVO> sltTchGuildMberList();

}