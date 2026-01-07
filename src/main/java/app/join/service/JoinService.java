package app.join.service;

import app.join.vo.JoinMemberVO;

public interface JoinService {

	boolean idDupleChk(String memberId);

	boolean joinMber(JoinMemberVO joinMemberVO);

}