package app.psn.std.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.vo.StdSessionVO;

@Mapper
public interface StdLoginMapper {

    /**
     * 학생 회원 사용자 정보 요청
     */
    public StdSessionVO sltMber(LoginVO loginVO);

}