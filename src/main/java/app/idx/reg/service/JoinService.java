package app.idx.reg.service;

import app.idx.reg.vo.JoinMemberVO;

public interface JoinService {

	boolean idDupleChk(String memberId);

	boolean joinMber(JoinMemberVO joinMemberVO);

}