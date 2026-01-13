package app.idx.reg.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.idx.reg.vo.JoinMemberVO;

@Mapper
public interface JoinMapper {

	public int idDupleChk(String memberId);

	public String sltMberSn();

	public int insMberInfo(JoinMemberVO joinMemberVO);

	public int insAvatarInfo(JoinMemberVO joinMemberVO);

	public String sltGuildInfo(JoinMemberVO joinMemberVO);

	// 1. MBER_INFO insert -> MBER_SN return;

	// 2.AVATAR_INFO insert

	// 3. 입력한 학년/반 정보로 GUILD_INFO 정보를 찾는다. -> GUILD_SN return;

	// 4.GUILD_MBER_LIST insert

}