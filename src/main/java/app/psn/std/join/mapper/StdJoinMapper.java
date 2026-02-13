package app.psn.std.join.mapper;

import app.psn.std.join.vo.StdJoinMemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StdJoinMapper {

	Integer idDupleChk(String memberId);

	String sltMberSn();

	void insMberInfo(StdJoinMemberVO stdJoinMemberVO);

	void insAvatarInfo(StdJoinMemberVO stdJoinMemberVO);

	void insGildMberList(StdJoinMemberVO stdJoinMemberVO);

	String sltGildInfo(StdJoinMemberVO stdJoinMemberVO);

}