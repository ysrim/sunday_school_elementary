package app.psn.std.login.service;

import app.psn.std.login.vo.LoginVO;
import app.psn.std.login.vo.SessionVO;

public interface LoginService {

    /**
     * 학생 사용자 정보 요청
     */
    SessionVO sltMber(LoginVO loginVO);

    /**
     * 로그인 요청
     */
    SessionVO loginAx(LoginVO loginVO);

}