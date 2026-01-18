package app.idx.reg.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.idx.reg.vo.JoinMemberVO;

@Mapper
public interface JoinMapper {

	int idDupleChk(String memberId);

	String sltMberSn();

	int insMberInfo(JoinMemberVO joinMemberVO);

	int insAvatarInfo(JoinMemberVO joinMemberVO);

	int insGuildMberList(JoinMemberVO joinMemberVO);

	String sltGuildInfo(JoinMemberVO joinMemberVO);

}