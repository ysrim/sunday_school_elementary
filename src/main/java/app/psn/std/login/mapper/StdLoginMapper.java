package app.psn.std.login.mapper;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.vo.StdSessionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StdLoginMapper {

	/**
	 * 학생 회원 사용자 정보 요청
	 */
	StdSessionVO sltMber(LoginVO loginVO);

}