package app.psn.std.join.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.join.vo.StdJoinMemberVO;

@Mapper
public interface StdJoinMapper {

	Integer idDupleChk(String memberId);

	String sltMberSn();

	void insMberInfo(StdJoinMemberVO stdJoinMemberVO);

	void insAvatarInfo(StdJoinMemberVO stdJoinMemberVO);

	void insGuildMberList(StdJoinMemberVO stdJoinMemberVO);

	String sltGuildInfo(StdJoinMemberVO stdJoinMemberVO);

}