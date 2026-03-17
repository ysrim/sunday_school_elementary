package app.psn.std.login.mapper;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.vo.StdSessionVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StdLoginMapper {

	/**
	 * 학생 회원 사용자 정보 요청
	 */
	StdSessionVO sltMber(LoginVO loginVO);

	/**
	 * 학생 사용자 정보 요청
	 */
	StdSessionVO sltTokenMber(@Param("refreshToken") String refreshToken);

	/**
	 * 리프레쉬 토큰 DB 저장
	 */
	void regRefreshToken(@Param("mberId") String mberId, @Param("refreshToken") String refreshToken);

}