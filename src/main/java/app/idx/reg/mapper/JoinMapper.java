package app.idx.reg.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.idx.reg.vo.JoinMemberVO;

@Mapper
public interface JoinMapper {

	Integer idDupleChk(String memberId);

	String sltMberSn();

	Integer insMberInfo(JoinMemberVO joinMemberVO);

	Integer insAvatarInfo(JoinMemberVO joinMemberVO);

	Integer insGuildMberList(JoinMemberVO joinMemberVO);

	String sltGuildInfo(JoinMemberVO joinMemberVO);

}