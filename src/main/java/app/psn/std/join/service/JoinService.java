package app.psn.std.join.service;

import app.psn.std.join.vo.JoinMemberVO;

public interface JoinService {

    /**
     * 아이디 중복 체크
     */
    boolean idDupleChk(String memberId);

    /**
     * 회원 가입
     */
    boolean joinMber(JoinMemberVO joinMemberVO);

}