package app.join.service.impl;

import org.springframework.stereotype.Repository;

import com.base.cmm.dao.SqlAbstractDAO;

import app.join.vo.JoinMemberVO;

@Repository("joinDAO")
public class JoinDAO extends SqlAbstractDAO {

	public int idDupleChk(String memberId) {
		return selectOne("JoinDAO.idDupleChk", memberId);
	}

	public int insMberInfo(JoinMemberVO joinMemberVO) {
		return insert("JoinDAO.insMberInfo", joinMemberVO);
	}

	public int insAvatarInfo(JoinMemberVO joinMemberVO) {
		return insert("JoinDAO.insAvatarInfo", joinMemberVO);
	}

	public String sltGuildInfo(JoinMemberVO joinMemberVO) {
		return selectOne("JoinDAO.sltGuildInfo", joinMemberVO);
	}

	// 1. MBER_INFO insert -> MBER_SN return;

	// 2.AVATAR_INFO insert

	// 3. 입력한 학년/반 정보로 GUILD_INFO 정보를 찾는다. -> GUILD_SN return;

	// 4.GUILD_MBER_LIST insert

}