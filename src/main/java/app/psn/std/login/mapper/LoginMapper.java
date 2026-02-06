package app.psn.std.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.login.vo.LoginVO;
import app.psn.std.login.vo.SessionVO;

@Mapper
public interface LoginMapper {

    /**
     * 학생 회원 사용자 정보 요청
     */
    public SessionVO sltMber(LoginVO loginVO);

}