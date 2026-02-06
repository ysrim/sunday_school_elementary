package app.psn.std.join.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.join.vo.JoinMemberVO;

@Mapper
public interface JoinMapper {

	Integer idDupleChk(String memberId);

	String sltMberSn();

	void insMberInfo(JoinMemberVO joinMemberVO);

	void insAvatarInfo(JoinMemberVO joinMemberVO);

	void insGuildMberList(JoinMemberVO joinMemberVO);

	String sltGuildInfo(JoinMemberVO joinMemberVO);

}