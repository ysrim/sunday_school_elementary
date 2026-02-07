package app.psn.mng.login.service;

import app.psn.mng.login.vo.MngLoginVO;
import app.psn.mng.login.vo.MngSessionVO;

public interface MngLoginService {

    /**
     * 관리자 사용자 정보 요청
     */
    MngSessionVO sltMber(MngLoginVO loginVO);

    /**
     * 로그인 요청
     */
    MngSessionVO loginAx(MngLoginVO loginVO);

}